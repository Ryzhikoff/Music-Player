package com.example.feature_playlists.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_playlists.ui.PlaylistsViewModel
import com.example.remote.domain.GetPlaylistsUseCase
import javax.inject.Inject

class PlaylistsViewModeFactory @Inject constructor(
    private val getPlaylistsUseCase: GetPlaylistsUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlaylistsViewModel::class.java)) {
                return PlaylistsViewModel(getPlaylistsUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }