package ru.vktracker.core.ui.text

import ru.vktracker.core.ui.view.common.AbstractView

/**
 * @author Danil Glazkov on 22.06.2023, 17:32
 */
interface Message {

    fun apply(view: AbstractView.Text)


    abstract class Abstract(private val text: String) : Message {
        override fun apply(view: AbstractView.Text) = view.apply(text)
    }

    data class Base(private val text: String) : Abstract(text)

    object Empty : Abstract("")
}