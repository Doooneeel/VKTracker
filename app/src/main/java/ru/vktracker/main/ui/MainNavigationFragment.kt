package ru.vktracker.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.core.ui.GenericOnItemSelectedListener
import ru.vktracker.core.ui.navigation.GenericMenuItem
import ru.vktracker.databinding.FragmentMainNavigationBinding as Binding

/**
 * @author Danil Glazkov on 01.06.2023, 22:28
 */
@AndroidEntryPoint
class MainNavigationFragment : BaseFragment<Binding, MainNavigationViewModel>(ID, Binding::inflate) {

    override val viewModel by viewModels<MainNavigationViewModel.Base>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainMenuItems = MainNavigationMenuItems()
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment)

        val navController = (navHostFragment as NavHostFragment).navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph

        binding.bottomNavigationView.setOnItemSelectedListener(
            GenericOnItemSelectedListener(mainMenuItems, navController) { tabPosition: Int ->
                viewModel.changeLastSelectedTab(tabPosition)
            }
        )

        viewModel.observeMenuItem(viewLifecycleOwner) { lastSelectedTab ->
            val item: GenericMenuItem = lastSelectedTab.selectedMenuItem(mainMenuItems)
            item.apply(binding.bottomNavigationView, navGraph)
        }

        viewModel.init(savedInstanceState == null)
    }


    companion object {
        private val ID = R.layout.fragment_main_navigation
    }
}