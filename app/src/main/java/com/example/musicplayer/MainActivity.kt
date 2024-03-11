package com.example.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.core.ui.models.TrackUi
import com.example.feature_player.domain.Init
import com.example.feature_player.domain.MediaPlayerState
import com.example.feature_player.domain.Paused
import com.example.feature_player.domain.PlayerController
import com.example.feature_player.domain.Playing
import com.example.feature_player.domain.Preparing
import com.example.feature_player.domain.Stopped
import com.example.musicplayer.databinding.ActivityMainBinding
import com.example.musicplayer.di.AppComponentProvider
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var playerController: PlayerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as AppComponentProvider)
            .getAppComponent()
            .inject(this)

        setContentView(binding.root)
        setPlayerStateListener()
        setPlayerClickListener()
    }

    private fun setPlayerStateListener() {
        binding.root.doOnLayout {
            lifecycleScope.launch {
                MediaPlayerState.stateListener.collectLatest { state ->
                    when (state) {
                        is Init -> hidePlayer()
                        is Paused -> pause(state.trackUi)
                        is Playing -> showPlayer(state.trackUi)
                        is Preparing -> {}
                        is Stopped -> hidePlayer()
                    }
                }
            }
        }
    }

    private fun hidePlayer() {
        binding.player.apply {
            isVisible = false
            animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.hide_player)
        }

    }

    private fun showPlayer(trackUi: TrackUi) {
        println("MainActivity showPlayer trackUi.isPlayeing ${trackUi.isPlaying} ")
        binding.player.apply {
            if (!isVisible) {
                isVisible = true
                binding.player.animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.show_player)
            }
            setContent(trackUi)
        }
    }

    private fun pause(trackUi: TrackUi) {
        println("MainActivity PAUSE - trackUi.isPlaying ${trackUi.isPlaying}")
        binding.player.setContent(trackUi)
    }

    private fun setPlayerClickListener() {
        binding.player.apply {
            setOnStopClickListener {
                playerController.stop(it)
            }

            setOnPlayPauseClickListener { track ->
                track.isPlaying = !track.isPlaying
                playerController.play(track)
            }
        }
    }

}
