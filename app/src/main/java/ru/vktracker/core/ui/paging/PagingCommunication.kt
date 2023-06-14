package ru.vktracker.core.ui.paging

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.liveData
import ru.vktracker.core.ui.Communication

/**
 * @author Danil Glazkov on 11.06.2023, 23:50
 */
interface PagingCommunication<T : Any> : Communication.Observe<PagingData<T>> {

    abstract class Abstract<T : Any>(
        private val providePagingConfig: ProvidePagingConfig,
        private val providePagingSource: ProvidePagingSource<T>
    ) : PagingCommunication<T> {

        private val liveData get() = Pager(providePagingConfig.config()) {
            providePagingSource.providePagingSource()
        }.liveData

        override fun observe(owner: LifecycleOwner, observer: Observer<PagingData<T>>) =
            liveData.observe(owner, observer)
    }
}