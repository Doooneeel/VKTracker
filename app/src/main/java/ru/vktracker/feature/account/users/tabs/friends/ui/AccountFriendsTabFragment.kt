package ru.vktracker.feature.account.users.tabs.friends.ui

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.databinding.UsersFragmentBinding
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersTabFragment

/**
 * @author Danil Glazkov on 09.06.2023, 22:51
 */
@AndroidEntryPoint
class AccountFriendsTabFragment : BaseAccountUsersTabFragment<AccountFriendsTabViewModel>() {

    override val viewModel by viewModels<AccountFriendsTabViewModel>()

    override val bind = UsersFragmentBinding::bind

}