package ru.vktracker.core.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import ru.vktracker.core.ui.viewmodel.BaseViewModel

/**
 * @author Danil Glazkov on 23.06.2023, 17:47
 */
abstract class BaseFragmentViewModel<VB : ViewBinding, VM : BaseViewModel>(layoutId: Int) :
    BaseFragment<VB>(layoutId), RegisterObservers<VM> {

    protected abstract val viewModel: VM

    override fun VM.registerObservers() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.registerObservers()
        viewModel.init(savedInstanceState == null)
    }
}