package ru.vktracker.main.ui

import androidx.fragment.app.viewModels
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.R
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.databinding.FragmentSettingsBinding as Binding
import javax.inject.Inject

/**
 * @author Danil Glazkov on 08.06.2023, 03:58
 */
//TODO move to another module
class SettingsFragment : BaseFragment<Binding, SettingsViewModel>(ID, Binding::inflate) {

    override val viewModel by viewModels<SettingsViewModel>()

    companion object {
        private val ID = R.layout.fragment_settings
    }
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
) : BaseViewModel.Abstract(dispatchers)