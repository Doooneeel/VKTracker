package ru.vktracker.main.ui

import ru.vktracker.core.ui.navigation.GenericMenuItem
import ru.vktracker.core.ui.navigation.GenericMenuItems

/**
 * @author Danil Glazkov on 15.06.2023, 21:27
 */
interface SelectedMenuItem {

    fun selectedMenuItem(source: GenericMenuItems): GenericMenuItem


    class Base(private val index: Int) : SelectedMenuItem {

        override fun selectedMenuItem(source: GenericMenuItems): GenericMenuItem {
            val items: List<GenericMenuItem> = source.asList()

            return items.getOrElse(index) {
                items.first()
            }
        }
    }

}