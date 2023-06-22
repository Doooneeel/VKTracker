package ru.vktracker.core.ui

import androidx.paging.PagingSource
import java.lang.Exception

/**
 * @author Danil Glazkov on 10.06.2023, 08:40
 */
interface HandleError<T> {

    fun handle(exception: Exception): T

    interface Paging<T : Any> : HandleError<PagingSource.LoadResult<Int, T>>

}