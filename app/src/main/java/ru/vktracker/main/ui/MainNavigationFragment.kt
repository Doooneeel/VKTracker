package ru.vktracker.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.core.ui.navigation.MenuItem
import ru.vktracker.databinding.FragmentMainNavigationBinding as Binding

/**
 * @author Danil Glazkov on 01.06.2023, 22:28
 */
@AndroidEntryPoint
class MainNavigationFragment : BaseFragment<Binding, MainNavigationViewModel>(ID, Binding::inflate) {

    private val mainMenuItems = MainNavigationMenuItems()

    override val viewModel by viewModels<MainNavigationViewModel.Base>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment)

        val navController = (navHostFragment as NavHostFragment).navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph

        binding.bottomNavigationView.setOnItemSelectedListener(
            MainOnItemSelectedListener(mainMenuItems, navController) { screenIndex: Int ->
                viewModel.changeLastSelectedScreen(screenIndex)
            }
        )

        viewModel.observeLastSelectedItem(viewLifecycleOwner) { lastSelectedScreen ->
            val item: MenuItem = lastSelectedScreen.lastSelectedMenuItem(source = mainMenuItems)
            item.apply(binding.bottomNavigationView, navGraph)
        }

        viewModel.init(savedInstanceState == null)
    }


    companion object {
        private val ID = R.layout.fragment_main_navigation
    }
}