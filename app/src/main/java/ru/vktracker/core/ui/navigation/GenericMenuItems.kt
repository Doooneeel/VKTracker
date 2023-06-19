package ru.vktracker.core.ui.navigation

import android.view.MenuItem
import ru.vktracker.core.common.AsList

/**
 * @author Danil Glazkov on 15.06.2023, 22:43
 */
interface GenericMenuItems : AsList<GenericMenuItem> {

    fun apply(menuItem: MenuItem, onSelect: (GenericMenuItem, Int) -> Unit): Boolean


    abstract class Abstract : GenericMenuItems {

        protected abstract val genericItems: List<GenericMenuItem>

        override fun apply(menuItem: MenuItem, onSelect: (GenericMenuItem, Int) -> Unit): Boolean {
            genericItems.forEachIndexed { index, genericItem ->
                if (genericItem.matches(menuItem.itemId)) {
                    onSelect.invoke(genericItem, index)
                    return true
                }
            }
            return false
        }

        override fun asList(): List<GenericMenuItem> = genericItems
    }

}