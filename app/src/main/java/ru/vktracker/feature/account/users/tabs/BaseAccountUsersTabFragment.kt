package ru.vktracker.feature.account.users.tabs

import android.os.Bundle
import android.view.View
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.core.ui.OnClickCallback
import ru.vktracker.databinding.UsersFragmentBinding
import ru.vktracker.feature.account.users.ui.AccountUserUi
import ru.vktracker.feature.account.users.ui.AccountUsersAdapter
import ru.vktracker.feature.account.users.ui.AccountUsersUi

/**
 * @author Danil Glazkov on 10.06.2023, 05:44
 */
abstract class BaseAccountUsersTabFragment<M : BaseAccountUsersTabViewModel> :
    BaseFragment<UsersFragmentBinding, M>(ID) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AccountUsersAdapter(
            object : OnClickCallback<AccountUserUi> {
                override fun onClick(data: AccountUserUi) = Unit
            },
            object : OnClickCallback<AccountUserUi> {
                override fun onClick(data: AccountUserUi) = viewModel.changeTrackingStatus(data)
            },
        )

        binding.recyclerView.adapter = adapter

        viewModel.observeAccountUsers(viewLifecycleOwner) { users: AccountUsersUi ->
            users.apply(adapter)
        }

        binding.swipeLayout.setOnRefreshListener {
            viewModel.fetchUsers()
            binding.swipeLayout.isRefreshing = false
        }

        viewModel.fetchUsers()
    }

    companion object {
        private val ID = R.layout.users_fragment
    }

}