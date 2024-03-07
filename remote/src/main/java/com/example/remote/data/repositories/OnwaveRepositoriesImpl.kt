package com.example.remote.data.repositories

import com.example.remote.data.OnwaveApi
import com.example.remote.models.dto.playlists.PlaylistsDto
import com.example.remote.models.dto.tracks.TracksDto
import retrofit2.Response
import javax.inject.Inject

class OnwaveRepositoriesImpl @Inject constructor(
    private val onwaveApi: OnwaveApi,
) : OnwaveRepository {
    override suspend fun getPlaylists(page: Int, pageSize: Int): Response<PlaylistsDto> =
        onwaveApi.getPlaylists(page, pageSize)

    override suspend fun getTrackByPlaylistId(page: Int, pageSize: Int, playlistId: Int): Response<TracksDto> =
        onwaveApi.getTracksByPlaylist(page = page, pageSize = pageSize, playlistId = playlistId)

}