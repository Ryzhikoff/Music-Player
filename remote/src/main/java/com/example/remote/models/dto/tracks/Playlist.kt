package com.example.remote.models.dto.tracks


import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("count")
    val count: Int,
    @SerializedName("date_create")
    val dateCreate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("genres")
    val genres: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("image_file_id")
    val imageFileId: Any?,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("is_public")
    val isPublic: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("user_id")
    val userId: Int
)