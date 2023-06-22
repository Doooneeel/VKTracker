package ru.vktracker.core.ui.navigation

import androidx.navigation.NavController

/**
 * @author Danil Glazkov on 21.06.2023, 14:33
 */
interface Screen {

    fun navigate(controller: NavController)


    abstract class Abstract (private val destination: Int): Screen {
        override fun navigate(controller: NavController) = controller.navigate(destination)
    }

    class Base(destination: Int) : Abstract(destination)

}