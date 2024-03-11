package com.example.remote.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.remote.data.repositories.OnwaveRepository
import com.example.remote.models.mappers.toTrackUi
import com.example.core.ui.models.TrackUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException

class TracksPagingSource @AssistedInject constructor(
    private val repository: OnwaveRepository,
    @Assisted private val playlistId: Int,
) : PagingSource<Int, com.example.core.ui.models.TrackUi>() {
    override fun getRefreshKey(state: PagingState<Int, com.example.core.ui.models.TrackUi>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.example.core.ui.models.TrackUi> {
        val page = params.key ?: INITIAL_PAGE_NUMBER
        val response = repository.getTrackByPlaylistId(page = page, pageSize = PAGE_SIZE, playlistId = playlistId)

        return if (response.isSuccessful && response.body() != null) {
            val playlistsUi = response.body()!!.body.tracks.map { it.toTrackUi() }

            val nextKey = if (response.body()!!.pagination.hasNextPage) page + 1 else null
            val prevKey = if (response.body()!!.pagination.hasPreviousPage) page - 1 else null
            LoadResult.Page(data = playlistsUi, prevKey = prevKey, nextKey = nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted playlistId: Int,): TracksPagingSource
    }

    private companion object {
        const val INITIAL_PAGE_NUMBER = 1
        const val PAGE_SIZE = 40
    }
}