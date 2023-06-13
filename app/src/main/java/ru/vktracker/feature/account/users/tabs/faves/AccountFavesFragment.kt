package ru.vktracker.feature.account.users.tabs.faves

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersFragment
import ru.vktracker.databinding.UsersFragmentBinding as Binding

/**
 * @author Danil Glazkov on 10.06.2023, 01:48
 */
@AndroidEntryPoint
class AccountFavesFragment : BaseAccountUsersFragment<AccountFavesViewModel>(Binding::inflate) {
    override val viewModel by viewModels<AccountFavesViewModel>()
}