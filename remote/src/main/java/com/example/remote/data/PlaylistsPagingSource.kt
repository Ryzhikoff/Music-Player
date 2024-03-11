package com.example.remote.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.remote.data.repositories.OnwaveRepository
import com.example.remote.models.mappers.toPlaylistsUi
import com.example.remote.models.ui.PlaylistUi
import retrofit2.HttpException
import javax.inject.Inject

class PlaylistsPagingSource @Inject constructor(
    private val repository: OnwaveRepository,

    ) : PagingSource<Int, PlaylistUi>() {
    override fun getRefreshKey(state: PagingState<Int, PlaylistUi>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlaylistUi> {
        val page = params.key ?: INITIAL_PAGE_NUMBER
        val response = repository.getPlaylists(page = page, pageSize = PAGE_SIZE)

        return if (response.isSuccessful && response.body() != null) {
            val playlistsUi = response.body()!!.body.playlists.map { it.toPlaylistsUi() }
            val nextKey = if (response.body()!!.pagination.hasNextPage) page + 1 else null
            val prevKey = if (response.body()!!.pagination.hasPreviousPage) page - 1 else null
            LoadResult.Page(data = playlistsUi, prevKey = prevKey, nextKey = nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }

    private companion object {
        const val INITIAL_PAGE_NUMBER = 1
        const val PAGE_SIZE = 30
    }
}