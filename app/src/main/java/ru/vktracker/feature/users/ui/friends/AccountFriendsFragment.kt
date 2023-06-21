package ru.vktracker.feature.users.ui.friends

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.feature.users.ui.BaseAccountUsersFragment
import ru.vktracker.databinding.FragmentUsersBinding as Binding

/**
 * @author Danil Glazkov on 09.06.2023, 22:51
 */
@AndroidEntryPoint
class AccountFriendsFragment : BaseAccountUsersFragment<AccountFriendsViewModel>(Binding::inflate) {
    override val viewModel by viewModels<AccountFriendsViewModel>()
}