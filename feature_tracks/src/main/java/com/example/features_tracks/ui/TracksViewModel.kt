package com.example.features_tracks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.remote.domain.GetTracksFromPlaylistUseCase
import com.example.core.ui.models.TrackUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class TracksViewModel @Inject constructor(
    private val getTracksFromPlaylistUseCase: GetTracksFromPlaylistUseCase
): ViewModel() {

    private val _tracksFlow: MutableStateFlow<PagingData<TrackUi>> = MutableStateFlow(PagingData.empty())
    val tracksFlow: StateFlow<PagingData<TrackUi>> = _tracksFlow.asStateFlow()

    fun getTracks(playlistId: Int) {
        viewModelScope.launch {
            getTracksFromPlaylistUseCase.execute(viewModelScope, playlistId = playlistId)
                .collectLatest {
                    _tracksFlow.emit(it)
                }
        }
    }
}