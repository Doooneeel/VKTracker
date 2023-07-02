package ru.vktracker.core.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import ru.vktracker.core.ui.viewmodel.BaseViewModel

/**
 * @author Danil Glazkov on 23.06.2023, 17:47
 */
abstract class BaseFragmentViewModel<VB : ViewBinding, VM : BaseViewModel>(
    layoutId: Int,
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BaseFragment<VB>(layoutId, inflate), RegisterObservers<VM> {

    protected abstract val viewModel: VM

    override fun VM.registerObservers() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.registerObservers()
        viewModel.init(savedInstanceState == null)
    }
}