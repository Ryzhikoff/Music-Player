package com.example.remote.models.mappers

import com.example.core.ui.models.TrackUi
import com.example.remote.models.dto.tracks.Track

fun Track.toTrackUi(): TrackUi =
    TrackUi(
        id = id,
        name = name,
        author = author,
        imageUrl = image,
        trackUrl = url,
    )