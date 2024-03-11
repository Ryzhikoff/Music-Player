package com.example.remote.models.dto.playlists


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("count")
    val count: Int,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("has_previous_page")
    val hasPreviousPage: Boolean,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("total_page")
    val totalPage: Int
)