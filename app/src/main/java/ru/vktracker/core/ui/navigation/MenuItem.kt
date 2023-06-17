package ru.vktracker.core.ui.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.vktracker.core.common.MatchesId

/**
 * @author Danil Glazkov on 15.06.2023, 22:42
 */
interface MenuItem : MatchesId {

    fun apply(navigationView: BottomNavigationView, graph: NavGraph)

    fun navigate(controller: NavController)


    class Base(
        @IdRes private val selectedItemId: Int,
        private val destination: Int,
    ) : MenuItem {

        override fun matches(id: Int): Boolean = id == selectedItemId

        override fun navigate(controller: NavController) {
            if (!controller.popBackStack(destination, false)) {
                controller.navigate(destination)
            }
        }

        override fun apply(navigationView: BottomNavigationView, graph: NavGraph) {
            navigationView.selectedItemId = selectedItemId
            graph.setStartDestination(destination)
        }
    }

}