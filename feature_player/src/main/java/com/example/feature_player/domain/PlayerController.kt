package com.example.feature_player.domain

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.core.ui.models.TrackUi
import com.example.feature_player.service.MediaPlayerService
import com.example.feature_player.service.MediaPlayerService.Companion.KEY_ACTIVITY_CLASS
import com.example.feature_player.service.MediaPlayerService.Companion.KEY_TRACK_URL
import com.example.feature_player.utils.isServiceRunning
import javax.inject.Inject

class PlayerController @Inject constructor(
    private val context: Context,
    private val activity: Class<out Activity>
) {


    fun play(trackUi: TrackUi) {
        println("PlayerController play  trackUi.id - ${trackUi.id} currentTrackId $currentTrackId")
        if (!isServiceRunning(context, MediaPlayerService::class.java)) {
            currentTrackId = trackUi.id
            val serviceIntent = Intent(context, MediaPlayerService::class.java).apply {
                putExtra(KEY_ACTIVITY_CLASS, activity.name)
            }
            context.startForegroundService(serviceIntent)
            MediaPlayerState.preparingPlay(trackUi)
        } else {
            if (trackUi.id == currentTrackId && !paused) {
                println("PlayerController need pause")
                paused = true
                MediaPlayerState.pause(trackUi)
            } else if (trackUi.id == currentTrackId && paused) {
                paused = false
                MediaPlayerState.playing(trackUi)
            } else {
                paused = false
                println("PlayerController START new track")
                MediaPlayerState.preparingPlay(trackUi)
            }
            currentTrackId = trackUi.id
        }
    }

    fun stop(trackUi: TrackUi) {
        if (isServiceRunning(context, MediaPlayerService::class.java)) {
            MediaPlayerState.stop(trackUi)
        }
    }

    companion object {
        private var currentTrackId = -1
        private var paused: Boolean = false
    }
}

