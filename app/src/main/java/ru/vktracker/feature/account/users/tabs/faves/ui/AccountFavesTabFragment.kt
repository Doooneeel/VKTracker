package ru.vktracker.feature.account.users.tabs.faves.ui

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.databinding.UsersFragmentBinding
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersTabFragment

/**
 * @author Danil Glazkov on 10.06.2023, 01:48
 */
@AndroidEntryPoint
class AccountFavesTabFragment : BaseAccountUsersTabFragment<AccountFavesTabViewModel>() {

    override val viewModel by viewModels<AccountFavesTabViewModel>()

    override val bind = UsersFragmentBinding::bind

}