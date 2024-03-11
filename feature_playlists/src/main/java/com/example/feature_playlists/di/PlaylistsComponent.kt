package com.example.feature_playlists.di

import com.example.feature_playlists.ui.PlaylistsFragment
import com.example.feature_playlists.ui.PlaylistsViewModel
import com.example.remote.di.modules.RemoteModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
    ]
)
interface PlaylistsComponent {
    fun inject(playlistsFragment: PlaylistsFragment)

    fun inject(playlistsViewModel: PlaylistsViewModel)
}