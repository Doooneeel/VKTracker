package ru.vktracker.core.ui.text

import ru.vktracker.core.ui.view.AbstractView

/**
 * @author Danil Glazkov on 22.06.2023, 17:32
 */
interface ErrorMessage {

    fun apply(view: AbstractView.Text)


    abstract class Abstract(private val error: String) : ErrorMessage {
        override fun apply(view: AbstractView.Text) = view.apply(error)
    }

    data class Base(private val error: String) : Abstract(error)

    object Empty : Abstract("")
}