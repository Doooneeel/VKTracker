package ru.vktracker.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragmentViewModel
import ru.vktracker.core.ui.navigation.LastPosition
import ru.vktracker.databinding.FragmentMainNavigationBinding as Binding

/**
 * @author Danil Glazkov on 01.06.2023, 22:28
 */
@AndroidEntryPoint
class MainNavigationFragment : BaseFragmentViewModel<Binding, MainNavigationViewModel>(ID, Binding::inflate) {

    override val viewModel by viewModels<MainNavigationViewModel.Base>()
    override val hasBottomBar = true

    override fun MainNavigationViewModel.registerObservers() {
        observeLastPosition(viewLifecycleOwner) { lastPosition: LastPosition ->
            lastPosition.apply(binding.bottomNavigationView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager.findFragmentById(R.id.tabs_nav_host_fragment) as NavHostFragment
        setupWithNavController(binding.bottomNavigationView, navHost.navController)
    }

    override fun onPause() {
        viewModel.changeLastSelectedTab(binding.bottomNavigationView.currentPosition())
        super.onPause()
    }

    companion object {
        private val ID = R.layout.fragment_main_navigation
    }
}