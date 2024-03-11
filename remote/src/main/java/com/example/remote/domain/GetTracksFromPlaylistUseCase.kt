package com.example.remote.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.ui.models.TrackUi
import com.example.remote.data.TracksPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetTracksFromPlaylistUseCase @Inject constructor(
    private val pagingSourceFactory: TracksPagingSource.Factory
) {

    fun execute(coroutineScope: CoroutineScope, playlistId: Int): StateFlow<PagingData<TrackUi>> =
        newPager(playlistId).flow
            .cachedIn(coroutineScope)
            .stateIn(coroutineScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(playlistId: Int): Pager<Int, TrackUi> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                maxSize = 100,
            ),
        ) {
            pagingSourceFactory.create(playlistId)
        }

}