package ru.vktracker.core.ui.view.common

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingData
import ru.vktracker.core.common.Clear

/**
 * @author Danil Glazkov on 02.06.2023, 12:14
 */
interface AbstractView {

    interface Text {
        fun apply(text: String)
    }

    interface ImageUrl {
        fun load(url: String)
    }

    interface ToggleButton {
        fun apply(toggle: Boolean)
    }

    interface List<T> {
        fun apply(items: kotlin.collections.List<T>)
    }

    interface PagingList<T : Any> {
        fun apply(scope: LifecycleCoroutineScope, data: PagingData<T>)
    }

    interface Position {

        fun apply(position: Int)

        fun currentPosition(): Int

    }

    interface Input {
        fun input(): String
    }

    interface Password : Clear {
        fun password(): CharArray
    }

}