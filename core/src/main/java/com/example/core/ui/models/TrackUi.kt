package com.example.core.ui.models

data class TrackUi(
    val id: Int,
    val name: String,
    val author: String,
    val imageUrl: String?,
    val trackUrl: String,
    var isPlaying: Boolean = false,
)