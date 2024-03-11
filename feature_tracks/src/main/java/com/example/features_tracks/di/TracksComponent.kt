package com.example.features_tracks.di

import com.example.feature_player.di.PlayerModule
import com.example.features_tracks.ui.TracksFragment
import com.example.features_tracks.ui.TracksViewModel
import com.example.remote.di.modules.RemoteModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        PlayerModule::class,
    ]
)
interface TracksComponent {

    fun inject(tracksViewModel: TracksViewModel)

    fun inject(tracksFragment: TracksFragment)
}