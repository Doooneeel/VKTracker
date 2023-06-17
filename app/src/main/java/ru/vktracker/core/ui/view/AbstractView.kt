package ru.vktracker.core.ui.view

import androidx.annotation.DrawableRes
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingData

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
        fun apply(lifecycle: Lifecycle, data: PagingData<T>)
    }

}