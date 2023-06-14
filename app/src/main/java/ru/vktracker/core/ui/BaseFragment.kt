package ru.vktracker.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

/**
 * @author Danil Glazkov on 01.06.2023, 03:54
 */
abstract class BaseFragment<VB : ViewBinding, M : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BaseFragmentNoViewModel<VB>(layoutId, inflate) {

    protected abstract val viewModel: M

}