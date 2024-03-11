package com.example.remote.data

import com.example.remote.models.dto.playlists.PlaylistsDto
import com.example.remote.models.dto.tracks.TracksDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OnwaveApi {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int,
    ): Response<PlaylistsDto>

    @GET("playlists/{playlistId}")
    suspend fun getTracksByPlaylist(
        @Path("playlistId") playlistId: Int,
        @Query("page") page: Int,
        @Query("limit") pageSize: Int,
    ): Response<TracksDto>
}