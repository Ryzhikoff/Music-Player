package com.example.remote.models.dto.playlists


import com.google.gson.annotations.SerializedName

data class Playlists(
    @SerializedName("count")
    val count: Int,
    @SerializedName("description")
    val description: String?,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("is_public")
    val isPublic: Boolean,
    @SerializedName("like_count")
    val likeCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("tags")
    val tags: List<Tag>,
    @SerializedName("user_id")
    val userId: Int
)