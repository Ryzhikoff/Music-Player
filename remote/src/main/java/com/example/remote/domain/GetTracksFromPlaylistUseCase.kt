package com.example.remote.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.remote.data.TracksPagingSource
import com.example.remote.models.ui.TracksUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetTracksFromPlaylistUseCase @Inject constructor(
    private val pagingSourceFactory: TracksPagingSource.Factory
) {

    fun execute(coroutineScope: CoroutineScope, playlistId: Int): StateFlow<PagingData<TracksUi>> =
        newPager(playlistId).flow
            .cachedIn(coroutineScope)
            .stateIn(coroutineScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(playlistId: Int): Pager<Int, TracksUi> =
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