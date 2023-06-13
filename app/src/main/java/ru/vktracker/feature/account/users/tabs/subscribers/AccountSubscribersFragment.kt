package ru.vktracker.feature.account.users.tabs.subscribers

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.databinding.UsersFragmentBinding as Binding
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersFragment

/**
 * @author Danil Glazkov on 10.06.2023, 01:24
 */
@AndroidEntryPoint
class AccountSubscribersFragment : BaseAccountUsersFragment<AccountSubscribersViewModel>(Binding::inflate) {
    override val viewModel by viewModels<AccountSubscribersViewModel>()
}