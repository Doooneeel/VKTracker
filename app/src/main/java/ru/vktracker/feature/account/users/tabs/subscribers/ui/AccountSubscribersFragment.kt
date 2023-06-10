package ru.vktracker.feature.account.users.tabs.subscribers.ui

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.databinding.UsersFragmentBinding
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersTabFragment

/**
 * @author Danil Glazkov on 10.06.2023, 01:24
 */
@AndroidEntryPoint
class AccountSubscribersTabFragment : BaseAccountUsersTabFragment<AccountSubscribersTabViewModel>() {

    override val viewModel by viewModels<AccountSubscribersTabViewModel>()

    override val bind = UsersFragmentBinding::bind

}