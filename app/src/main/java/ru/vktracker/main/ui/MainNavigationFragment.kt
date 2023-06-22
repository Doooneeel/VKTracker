package ru.vktracker.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.databinding.FragmentMainNavigationBinding as Binding

/**
 * @author Danil Glazkov on 01.06.2023, 22:28
 */
@AndroidEntryPoint
class MainNavigationFragment : BaseFragment<Binding, MainNavigationViewModel>(ID, Binding::inflate) {

    override val viewModel by viewModels<MainNavigationViewModel.Base>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.navigationBarColor =
            SurfaceColors.SURFACE_2.getColor(requireContext())

        val navHost = childFragmentManager.findFragmentById(R.id.tabs_nav_host_fragment) as NavHostFragment

        setupWithNavController(binding.bottomNavigationView, navHost.navController)

        viewModel.observeLastPosition(viewLifecycleOwner) { lastPosition ->
            lastPosition.apply(binding.bottomNavigationView)
        }

        viewModel.init(savedInstanceState == null)
    }

    override fun onPause() {
        viewModel.changeLastSelectedTab(binding.bottomNavigationView.currentPosition())
        super.onPause()
    }

    companion object {
        private val ID = R.layout.fragment_main_navigation
    }
}