package com.example.remote.models.dto.tracks


import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("playlist")
    val playlist: Playlist,
    @SerializedName("tracks")
    val tracks: List<Track>
)