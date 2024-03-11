package com.example.remote.models.dto.playlists


import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("playlists")
    val playlists: List<Playlists>
)