package com.example.remote.data.repositories

import com.example.remote.models.dto.playlists.PlaylistsDto
import com.example.remote.models.dto.tracks.TracksDto
import retrofit2.Response

interface OnwaveRepository {
    suspend fun getPlaylists(page: Int, pageSize: Int): Response<PlaylistsDto>

    suspend fun getTrackByPlaylistId(page: Int, pageSize: Int, playlistId: Int): Response<TracksDto>
}