package com.example.musicplayer

import android.app.Application
import com.example.feature_player.di.DaggerPlayerComponent
import com.example.feature_player.di.PlayerComponent
import com.example.feature_player.di.PlayerComponentProvider
import com.example.feature_player.di.PlayerModule
import com.example.feature_playlists.di.DaggerPlaylistsComponent
import com.example.feature_playlists.di.PlaylistsComponent
import com.example.feature_playlists.di.PlaylistsComponentProvider
import com.example.features_tracks.di.DaggerTracksComponent
import com.example.features_tracks.di.TrackComponentProvider
import com.example.features_tracks.di.TracksComponent
import com.example.musicplayer.di.AppComponent
import com.example.musicplayer.di.AppComponentProvider
import com.example.musicplayer.di.DaggerAppComponent
import com.example.remote.di.modules.RemoteModule

class App : Application(),
    PlaylistsComponentProvider,
    TrackComponentProvider,
    PlayerComponentProvider,
    AppComponentProvider {

    private val remoteModule by lazy {
        RemoteModule()
    }

    private val playerModule by lazy {
        PlayerModule(this, MainActivity::class.java)
    }
    override fun getPlaylistsComponent(): PlaylistsComponent =
        DaggerPlaylistsComponent.builder()
            .remoteModule(remoteModule)
            .build()

    override fun getTrackComponent(): TracksComponent =
        DaggerTracksComponent.builder()
            .remoteModule(remoteModule)
            .playerModule(playerModule)
            .build()

    override fun getPlayerComponent(): PlayerComponent =
        DaggerPlayerComponent.builder()
            .playerModule(playerModule)
            .build()

    override fun getAppComponent(): AppComponent =
        DaggerAppComponent.builder()
            .playerModule(playerModule)
            .build()
}