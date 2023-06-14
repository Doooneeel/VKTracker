package ru.vktracker.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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

        val nestedController: NavController = NavHostFragment.findNavController(
            childFragmentManager.findFragmentById(binding.navHostFragment.id)!!
        )

        binding.bottomNavigationView.setOnItemSelectedListener(
            MainOnItemSelectedListener(nestedController)
        )
    }

    companion object {
        private val ID = R.layout.fragment_main_navigation
    }
}