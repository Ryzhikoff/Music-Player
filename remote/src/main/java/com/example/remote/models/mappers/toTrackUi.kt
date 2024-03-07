package com.example.remote.models.mappers

import com.example.remote.models.dto.tracks.Track
import com.example.remote.models.ui.TracksUi

fun Track.toTrackUi(): TracksUi =
    TracksUi(
        id = id,
        name = name,
        author = author,
        imageUrl = image,
        trackUrl = url,
    )