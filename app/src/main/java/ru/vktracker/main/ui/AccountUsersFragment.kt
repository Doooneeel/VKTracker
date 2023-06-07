package ru.vktracker.main.ui


import androidx.fragment.app.viewModels
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.R
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.core.ui.BaseViewModel
import javax.inject.Inject
import ru.vktracker.databinding.FragmentAccountUsersBinding as Binding

/**
 * @author Danil Glazkov on 08.06.2023, 03:49
 */
//TODO move to another module
class AccountUsersFragment : BaseFragment<Binding, AccountUsersViewModel>(ID) {

    override val bind = Binding::bind

    override val viewModel by viewModels<AccountUsersViewModel>()

    companion object {
        private val ID = R.layout.fragment_account_users
    }
}

@HiltViewModel
class AccountUsersViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
) : BaseViewModel.Abstract(dispatchers)