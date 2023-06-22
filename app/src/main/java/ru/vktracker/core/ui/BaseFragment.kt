package ru.vktracker.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @author Danil Glazkov on 01.06.2023, 03:54
 */
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment(layoutId) {

    protected abstract val viewModel: VM

    protected val binding: VB get() = checkNotNull(_binding) {
        "${ this::class.java.name }: Binding not initialized"
    }
    private var _binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        _binding = inflate.invoke(inflater, container, false)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}