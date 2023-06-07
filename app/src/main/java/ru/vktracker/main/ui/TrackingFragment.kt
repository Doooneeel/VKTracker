package ru.vktracker.main.ui

import androidx.fragment.app.viewModels
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.R
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.databinding.FragmentTrackingBinding as Binding
import javax.inject.Inject

/**
 * @author Danil Glazkov on 08.06.2023, 03:55
 */
//TODO move to another module
class TrackingFragment : BaseFragment<Binding, TrackingViewModel>(ID) {

    override val bind = Binding::bind

    override val viewModel by viewModels<TrackingViewModel>()

    companion object {
        private val ID = R.layout.fragment_tracking
    }
}

@HiltViewModel
class TrackingViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
) : BaseViewModel.Abstract(dispatchers)