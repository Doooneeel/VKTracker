package ru.vktracker.core.ui.navigation

import ru.vktracker.core.ui.view.common.AbstractView

/**
 * @author Danil Glazkov on 21.06.2023, 15:51
 */
interface LastPosition {

    fun apply(view: AbstractView.Position)


    data class Base(private val position: Int) : LastPosition {
        override fun apply(view: AbstractView.Position) = view.apply(position)
    }
}