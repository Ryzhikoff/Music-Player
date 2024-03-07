package com.example.remote.models.dto.playlists


import com.google.gson.annotations.SerializedName

data class PlaylistsDto(
    @SerializedName("body")
    val body: Body,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("generated")
    val generated: Double,
    @SerializedName("pagination")
    val pagination: Pagination,
    @SerializedName("status")
    val status: Boolean
)