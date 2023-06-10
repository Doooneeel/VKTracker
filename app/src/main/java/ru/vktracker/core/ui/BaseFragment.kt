package ru.vktracker.core.ui

import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

/**
 * @author Danil Glazkov on 01.06.2023, 03:54
 */
abstract class BaseFragment<B : ViewBinding, M : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : BaseFragmentNoViewModel<B>(layoutId) {

    protected abstract val viewModel: M

}