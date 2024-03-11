package com.example.feature_playlists.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remote.domain.GetPlaylistsUseCase
import javax.inject.Inject

class PlaylistsViewModel @Inject constructor(
    getPlaylistsUseCase: GetPlaylistsUseCase,
)  : ViewModel() {

    val playlists = getPlaylistsUseCase.execute(viewModelScope)

}