package ru.vktracker.core.ui

import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @author Danil Glazkov on 10.06.2023, 03:28
 */
abstract class BaseFragmentNoViewModel<B : ViewBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment(layoutId) {

    protected abstract val bind: (View) -> B

    protected val binding by lazy {
        val root: View = requireView()
        bind(root)
    }
}