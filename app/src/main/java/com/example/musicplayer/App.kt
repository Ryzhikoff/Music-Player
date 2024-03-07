package com.example.musicplayer

import android.app.Application
import com.example.feature_playlists.di.DaggerPlaylistsComponent
import com.example.feature_playlists.di.PlaylistsComponent
import com.example.feature_playlists.di.PlaylistsComponentProvider
import com.example.features_tracks.di.DaggerTracksComponent
import com.example.features_tracks.di.TrackComponentProvider
import com.example.features_tracks.di.TracksComponent
import com.example.remote.di.modules.RemoteModule

class App : Application(),
    PlaylistsComponentProvider,
    TrackComponentProvider {

    private val remoteModule by lazy {
        RemoteModule()
    }
    override fun getPlaylistsComponent(): PlaylistsComponent =
        DaggerPlaylistsComponent.builder()
            .remoteModule(remoteModule)
            .build()

    override fun getTrackComponent(): TracksComponent =
        DaggerTracksComponent.builder()
            .remoteModule(remoteModule)
            .build()
}