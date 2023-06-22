package ru.vktracker.main.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.navigation.Screen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null
    private val viewModel: MainViewModel by viewModels<MainViewModel.Base>()

    private val topLevelDestinations = setOf(R.id.navigation_tabs_fragment, R.id.navigation_sign_in_fragment)

    private val destinationListener = NavController.OnDestinationChangedListener { _, destination, _ ->
        supportActionBar?.setDisplayHomeAsUpEnabled(!isStartDestination(destination))
    }

    private val fragmentListener = MainActivityFragmentLifecycleCallback { navController ->
        if (this.navController != navController) {
            this.navController?.removeOnDestinationChangedListener(destinationListener)
            navController.addOnDestinationChangedListener(destinationListener)
            this.navController = navController
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        //todo make authorization
        if (!VK.isLoggedIn()) {
            VK.login(this) {}.launch(listOf(VKScope.FRIENDS, VKScope.WALL))
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
        val navController = (navHostFragment as NavHostFragment).navController

        val graph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navController.graph = graph

        viewModel.observeScreen(this) { screen: Screen ->
            //todo apply start destination
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)
        viewModel.init(savedInstanceState == null)
    }


    //todo fix back pressed
    override fun onBackPressed() {
        if (isStartDestination(navController?.currentDestination)) {
            onBackPressedDispatcher.onBackPressed()
        } else {
            navController?.popBackStack()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: false || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        navController = null
        super.onDestroy()
    }

    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        val graph: NavGraph = destination.parent ?: return false
        val startDestinations = topLevelDestinations + graph.startDestinationId
        return startDestinations.contains(destination.id)
    }
}