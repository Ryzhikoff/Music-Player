package com.example.remote.data.repositories

import com.example.remote.models.dto.playlists.PlaylistsDto
import retrofit2.Response

interface PlaylistsRepository {
    suspend fun getPlaylists(page: Int, pageSize: Int): Response<PlaylistsDto>
}