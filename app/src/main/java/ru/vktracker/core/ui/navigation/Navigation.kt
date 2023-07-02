package ru.vktracker.core.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * @author Danil Glazkov on 21.06.2023, 14:33
 */
interface Navigation {

    fun navigate(controller: NavController)


    object Unit : Navigation {
        override fun navigate(controller: NavController) = kotlin.Unit
    }

    class ID (private val destinationId: Int) : Navigation {
        override fun navigate(controller: NavController) = controller.navigate(destinationId)
    }

    class Direction (private val navDirections: NavDirections) : Navigation {
        override fun navigate(controller: NavController) = controller.navigate(navDirections)
    }

}