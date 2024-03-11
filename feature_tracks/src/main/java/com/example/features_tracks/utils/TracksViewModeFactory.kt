package com.example.features_tracks.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.features_tracks.ui.TracksViewModel
import com.example.remote.domain.GetTracksFromPlaylistUseCase
import javax.inject.Inject

class TracksViewModeFactory @Inject constructor(
    private val getTracksFromPlaylistUseCase: GetTracksFromPlaylistUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TracksViewModel::class.java)) {
                return TracksViewModel(getTracksFromPlaylistUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }