package ru.vktracker.core.ui.paging

import androidx.paging.PagingSource

/**
 * @author Danil Glazkov on 12.06.2023, 07:58
 */
fun interface ProvidePagingSource<T : Any> {
    fun providePagingSource(): PagingSource<Int, T>
}