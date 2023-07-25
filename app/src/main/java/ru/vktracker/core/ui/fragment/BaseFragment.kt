package ru.vktracker.core.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.elevation.SurfaceColors
import ru.vktracker.R
import ru.vktracker.core.ui.ManageKeyboard
import ru.vktracker.core.ui.view.common.Throttle

/**
 * @author Danil Glazkov on 01.06.2023, 03:54
 */
abstract class BaseFragment<VB : ViewBinding>(layoutId: Int) : Fragment(layoutId),
    ManageKeyboard by ManageKeyboard.Base,
    RegisterListeners<VB>,
    HandleFirstRun
{
    protected abstract val binding: VB

    protected open val hasBottomBar: Boolean = false
    protected open val throttle: Throttle = Throttle.Base()

    protected val navController: NavController by lazy {
        NavHostFragment.findNavController(fragment = this)
    }

    private val inputMethodManager: InputMethodManager by lazy {
        requireContext().getSystemService(InputMethodManager::class.java)
    }

    protected val mainNavController: NavController by lazy {
        val activityFragmentManager = requireActivity().supportFragmentManager
        val fragment = activityFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
        val navHostFragment = fragment as NavHostFragment

        navHostFragment.navController
    }

    override fun handleFirstRun() = Unit
    override fun VB.registerListeners() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.navigationBarColor = if (hasBottomBar) {
            SurfaceColors.SURFACE_2.getColor(requireContext())
        } else {
            resources.getColor(android.R.color.transparent, requireContext().theme)
        }
        binding.registerListeners()

        if (savedInstanceState == null) {
            handleFirstRun()
        }
    }
}