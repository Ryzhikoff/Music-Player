package com.example.feature_player.di

import com.example.feature_player.service.MediaPlayerService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PlayerModule::class,
    ]
)
interface PlayerComponent {
    fun inject(playerService: MediaPlayerService)
}