package com.example.remote.data.repositories

import com.example.remote.data.OnwaveApi
import com.example.remote.models.dto.playlists.PlaylistsDto
import retrofit2.Response
import javax.inject.Inject

class PlaylistsRepositoriesImpl @Inject constructor(
    private val onwaveApi: OnwaveApi,
) : PlaylistsRepository {
    override suspend fun getPlaylists(page: Int, pageSize: Int): Response<PlaylistsDto> {

        println("PlaylistsRepositoriesImpl getPlaylists")
        val response = onwaveApi.getPlaylists(page, pageSize)
        println(response.body())
        return response
    }

}