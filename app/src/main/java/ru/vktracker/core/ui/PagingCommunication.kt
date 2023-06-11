package ru.vktracker.core.ui

import androidx.paging.PagingData

/**
 * @author Danil Glazkov on 11.06.2023, 23:50
 */
interface PagingCommunication<T : Any> : Communication.Mutable<PagingData<T>> {
    class Base<T : Any> : Communication.Ui<PagingData<T>>(), PagingCommunication<T>
}