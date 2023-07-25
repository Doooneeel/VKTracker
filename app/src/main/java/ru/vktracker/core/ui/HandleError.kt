package ru.vktracker.core.ui

import androidx.paging.PagingSource
import java.lang.Exception

/**
 * @author Danil Glazkov on 10.06.2023, 08:40
 */
interface HandleError<E : Exception, T> {

    fun handle(exception: E): T

    interface Unit<E : Exception> : HandleError<E, kotlin.Unit>

    interface Paging<E : Exception, T : Any> : HandleError<E, PagingSource.LoadResult<Int, T>>

}