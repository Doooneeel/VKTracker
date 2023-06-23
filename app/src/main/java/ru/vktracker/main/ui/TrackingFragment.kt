package ru.vktracker.main.ui

import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.databinding.FragmentTrackingBinding as Binding

/**
 * @author Danil Glazkov on 08.06.2023, 03:55
 */
//TODO move to another module
class TrackingFragment : BaseFragment<Binding>(ID, Binding::inflate) {

    override val hasBottomBar: Boolean = true

    companion object {
        private val ID = R.layout.fragment_tracking
    }
}