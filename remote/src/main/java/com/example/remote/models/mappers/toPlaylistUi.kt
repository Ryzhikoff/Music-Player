package com.example.remote.models.mappers

import com.example.remote.models.dto.playlists.Playlists
import com.example.remote.models.ui.PlaylistUi

fun Playlists.toPlaylistsUi(): PlaylistUi =
    PlaylistUi(
        id = id, name = name, imageUrl = imageUrl
    )