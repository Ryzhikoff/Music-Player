package com.example.remote.models.dto.tracks


import com.google.gson.annotations.SerializedName

data class TracksDto(
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