package ru.vktracker.core.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.vktracker.core.common.Page

/**
 * @author Danil Glazkov on 12.06.2023, 05:48
 */
abstract class VkApiPagingSource<T : Any> : PagingSource<Int, T>() {

    protected abstract suspend fun request(page: Page): List<T>

    protected abstract fun handleError(exception: Exception): LoadResult<Int, T>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> = try {
        val offset = params.key ?: 0
        val count = params.loadSize

        val data: List<T> = request(Page.Base(offset, count))
        val nextKey: Int? = if (data.size < count) null else offset + data.size

        LoadResult.Page(data, null, nextKey)
    } catch (exception: Exception) {
        handleError(exception)
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return null
    }
}