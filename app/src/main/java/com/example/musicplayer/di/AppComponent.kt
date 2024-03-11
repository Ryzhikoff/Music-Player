package com.example.musicplayer.di

import com.example.feature_player.di.PlayerModule
import com.example.musicplayer.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PlayerModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}