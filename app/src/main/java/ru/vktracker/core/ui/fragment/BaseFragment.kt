package ru.vktracker.core.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.elevation.SurfaceColors
import ru.vktracker.R
import ru.vktracker.core.ui.view.common.Throttle

/**
 * @author Danil Glazkov on 01.06.2023, 03:54
 */
abstract class BaseFragment<VB : ViewBinding>(
    @LayoutRes private val layoutId: Int,
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment(layoutId), RegisterListeners<VB>,
    ManageKeyboard,
    HandleFirstRun
{
    protected open val hasBottomBar: Boolean = false
    protected open val throttle: Throttle = Throttle.Base()

    protected val navController: NavController by lazy {
        NavHostFragment.findNavController(fragment = this)
    }

    private val inputMethodManager: InputMethodManager by lazy {
        requireActivity().getSystemService(InputMethodManager::class.java)
    }

    protected val mainNavController: NavController by lazy {
        val activityFragmentManager = requireActivity().supportFragmentManager
        val fragment = activityFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
        val navHostFragment = fragment as NavHostFragment

        navHostFragment.navController
    }

    private var innerBinding: VB? = null
    protected val binding: VB get() = checkNotNull(innerBinding)

    override fun handleFirstRun() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerListeners(throttle)

        if (savedInstanceState == null) {
            handleFirstRun()
        }
    }


    override fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, HIDE_NOT_ALWAYS)
    }

    override fun showKeyboard(view: View) {
        inputMethodManager.showSoftInput(view, SHOW_IMPLICIT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) : View {

        requireActivity().window.navigationBarColor = if (hasBottomBar) {
            SurfaceColors.SURFACE_2.getColor(requireContext())
        } else {
            resources.getColor(android.R.color.transparent, requireContext().theme)
        }

        innerBinding = inflate.invoke(inflater, container, false)
        return innerBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        innerBinding = null
    }

}