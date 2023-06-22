package ru.vktracker.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

/**
 * @author Danil Glazkov on 21.06.2023, 14:12
 */
class MainActivityFragmentLifecycleCallback(
    private val onNavControllerActivated: (NavController) -> Unit,
) : FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentViewCreated(
        fragmentMaanger: FragmentManager,
        fragment: Fragment,
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onFragmentViewCreated(fragmentMaanger, fragment, view, savedInstanceState)

        if (fragment !is MainNavigationFragment && fragment !is NavHostFragment) {
            onNavControllerActivated.invoke(fragment.findNavController())
        }
    }
}