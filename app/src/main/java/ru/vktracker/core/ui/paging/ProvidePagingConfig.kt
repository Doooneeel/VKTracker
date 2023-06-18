package ru.vktracker.core.ui.paging

import androidx.paging.PagingConfig

/**
 * @author Danil Glazkov on 12.06.2023, 05:54
 */
interface ProvidePagingConfig {

    fun config(): PagingConfig


    class Base : ProvidePagingConfig {
        override fun config() = PagingConfig(
            pageSize = 5,
            initialLoadSize = 8,
            enablePlaceholders = true
        )
    }
}