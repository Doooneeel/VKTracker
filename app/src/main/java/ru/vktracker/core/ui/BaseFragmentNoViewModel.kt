package ru.vktracker.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @author Danil Glazkov on 15.06.2023, 00:02
 */
abstract class BaseFragmentNoViewModel<VB : ViewBinding>(
    @LayoutRes private val layoutId: Int,
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment(layoutId) {

    protected val binding: VB get() = checkNotNull(_binding)
    private var _binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}