package ru.vktracker.core.ui

/**
 * @author Danil Glazkov on 10.06.2023, 07:15
 */
interface AbstractTabs {

    fun tabs(): List<AbstractTab>

    class Base(private val tabs: List<AbstractTab>) : AbstractTabs {
        override fun tabs(): List<AbstractTab> = tabs
    }

}