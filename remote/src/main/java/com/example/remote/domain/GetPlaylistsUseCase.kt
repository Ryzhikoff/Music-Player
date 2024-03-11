package com.example.remote.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.remote.models.ui.PlaylistUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetPlaylistsUseCase @Inject constructor(
    private val pagingSource: PagingSource<Int, PlaylistUi>,
) {

    fun execute(coroutineScope: CoroutineScope): StateFlow<PagingData<PlaylistUi>> =
        newPager().flow
            .cachedIn(coroutineScope)
            .stateIn(coroutineScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(): Pager<Int, PlaylistUi> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                maxSize = 100,
            ),
        ) {
            pagingSource
        }
}