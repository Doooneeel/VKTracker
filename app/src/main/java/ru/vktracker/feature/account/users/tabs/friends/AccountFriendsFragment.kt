package ru.vktracker.feature.account.users.tabs.friends

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.databinding.UsersFragmentBinding as Binding
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersFragment

/**
 * @author Danil Glazkov on 09.06.2023, 22:51
 */
@AndroidEntryPoint
class AccountFriendsFragment : BaseAccountUsersFragment<AccountFriendsViewModel>(Binding::inflate) {
    override val viewModel by viewModels<AccountFriendsViewModel>()
}