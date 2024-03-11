package com.example.remote.models.dto.tracks


import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("author")
    val author: String,
    @SerializedName("bpm")
    val bpm: Int,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_adv")
    val isAdv: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("playlist_id")
    val playlistId: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("user_id")
    val userId: Any?,
    @SerializedName("yoga_id")
    val yogaId: Int
)