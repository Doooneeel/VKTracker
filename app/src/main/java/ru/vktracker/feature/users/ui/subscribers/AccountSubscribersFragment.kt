package ru.vktracker.feature.users.ui.subscribers

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.feature.users.ui.BaseAccountUsersFragment
import ru.vktracker.databinding.FragmentUsersBinding as Binding

/**
 * @author Danil Glazkov on 10.06.2023, 01:24
 */
@AndroidEntryPoint
class AccountSubscribersFragment : BaseAccountUsersFragment<AccountSubscribersViewModel>(Binding::inflate) {
    override val viewModel by viewModels<AccountSubscribersViewModel>()
}