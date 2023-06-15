package ru.vktracker.main.ui

import ru.vktracker.core.ui.navigation.MenuItem
import ru.vktracker.core.ui.navigation.MenuItems

/**
 * @author Danil Glazkov on 15.06.2023, 21:27
 */
interface LastSelectedMenuItem {

    fun lastSelectedMenuItem(source: MenuItems): MenuItem


    class Base(private val index: Int) : LastSelectedMenuItem {

        override fun lastSelectedMenuItem(source: MenuItems): MenuItem {
            val items: List<MenuItem> = source.items()

            return items.getOrElse(index) {
                items.first()
            }
        }
    }

}