package ru.vktracker.core.ui.paging

import androidx.paging.PagingConfig

/**
 * @author Danil Glazkov on 12.06.2023, 05:54
 */
interface ProvidePagingConfig {

    fun config(): PagingConfig

    class VkApiFriends : ProvidePagingConfig {

        override fun config() = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = INIT_LOAD_SIZE,
            enablePlaceholders = true
        )

        companion object {
            private const val PAGE_SIZE = 5
            private const val INIT_LOAD_SIZE = 8
        }
    }
}