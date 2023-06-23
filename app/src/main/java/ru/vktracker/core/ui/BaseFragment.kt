package ru.vktracker.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.elevation.SurfaceColors
import ru.vktracker.R

/**
 * @author Danil Glazkov on 01.06.2023, 03:54
 */
abstract class BaseFragment <VB : ViewBinding>(
    @LayoutRes private val layoutId: Int,
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment(layoutId), RegisterClickListeners<VB> {

    protected open val hasBottomBar: Boolean = false
    protected open val throttle: Throttle = Throttle.Base()

    protected val navController: NavController by lazy {
        NavHostFragment.findNavController(fragment = this)
    }

    protected val mainNavController: NavController by lazy {
        val activityFragmentManager = requireActivity().supportFragmentManager
        val fragment = activityFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
        val navHostFragment = fragment as NavHostFragment

        navHostFragment.navController
    }

    private var innerBinding: VB? = null
    protected val binding: VB get() = checkNotNull(innerBinding)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().window.navigationBarColor = if (hasBottomBar) {
            SurfaceColors.SURFACE_2.getColor(context)
        } else {
            resources.getColor(android.R.color.transparent, context.theme)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerClickListeners(throttle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        innerBinding = inflate.invoke(inflater, container, false)
        return innerBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        innerBinding = null
    }

}