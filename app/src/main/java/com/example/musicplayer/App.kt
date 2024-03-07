package com.example.musicplayer

import android.app.Application
import com.example.feature_playlists.di.DaggerPlaylistsComponent
import com.example.feature_playlists.di.PlaylistsComponent
import com.example.feature_playlists.di.PlaylistsComponentProvider
import com.example.remote.di.modules.RemoteModule

class App : Application(), PlaylistsComponentProvider {

    private val remoteModule by lazy {
        RemoteModule()
    }
    override fun getPlaylistsComponent(): PlaylistsComponent =
        DaggerPlaylistsComponent.builder()
            .remoteModule(remoteModule)
            .build()
}