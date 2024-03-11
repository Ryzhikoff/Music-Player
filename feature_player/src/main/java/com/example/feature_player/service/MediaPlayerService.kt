package com.example.feature_player.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import com.example.core.ui.models.TrackUi
import com.example.feature_player.di.PlayerComponentProvider
import com.example.feature_player.domain.MediaPlayerState
import com.example.feature_player.domain.Paused
import com.example.feature_player.domain.Playing
import com.example.feature_player.domain.Preparing
import com.example.feature_player.domain.Stopped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MediaPlayerService : Service() {

    private var activityClass: Class<*>? = null

    private lateinit var notificationManager: NotificationManager

    private val mediaPlayer: MediaPlayer = MediaPlayer()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private val notificationBuilder by lazy {
        createNotificationBuilder()
    }

    override fun onCreate() {
        super.onCreate()
        (application as PlayerComponentProvider)
            .getPlayerComponent()
            .inject(this)

        createNotificationChannel()
    }

    private fun createNotificationBuilder(): NotificationCompat.Builder {
        val intent = Intent(this, activityClass)
        val pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Cool title")
            .setContentText("PLaying")
            .setContentIntent(pendingIntent)
            .setOngoing(true)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val activityClassName = intent?.getStringExtra(KEY_ACTIVITY_CLASS)
        activityClass = try {
            Class.forName(activityClassName!!)
        } catch (e: ClassNotFoundException) {
            null
        }

        ServiceCompat.startForeground(
            this,
            NOTIFICATION_ID,
            notificationBuilder.build(),
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
            } else {
                0
            }
        )

        playerStateListener()

        return START_NOT_STICKY
    }


    private fun playerStateListener() {
        coroutineScope.launch {
            MediaPlayerState.stateListener.collectLatest { state ->
                when (state) {
                    is Preparing -> startNewTrack(state.trackUi)
                    is Paused -> mediaPlayer.pause()
                    is Stopped -> stop()
                    is Playing -> mediaPlayer.start()
                    else -> println("actionListeners ELSE $state")
                }
            }
        }
    }

    private fun stop() {
        mediaPlayer.stop()
        stopSelf()
    }

    private fun startNewTrack(trackUi: TrackUi) {

        println("MediaPlayerService startNewTrack url - ${trackUi.name}")
        mediaPlayer.apply {
            reset()
            setDataSource(applicationContext, Uri.parse(trackUi.trackUrl))
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            prepareAsync()
            setOnPreparedListener {
                MediaPlayerState.playing(trackUi)
            }
        }
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_DESCRIPTION,
            NotificationManager.IMPORTANCE_LOW
        )

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(serviceChannel)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    companion object {
        const val CHANNEL_ID = "CHANNEL_UPDATE_DB"
        const val CHANNEL_DESCRIPTION = "Channel for update Database in app CallStatistic"
        const val NOTIFICATION_ID = 1
        const val REQUEST_CODE = 0
        const val KEY_TRACK_URL = "key_track_url"
        const val KEY_ACTIVITY_CLASS = "key_activity_class"
    }
}
