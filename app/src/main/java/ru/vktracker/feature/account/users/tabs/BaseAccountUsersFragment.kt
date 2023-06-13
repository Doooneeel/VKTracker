package ru.vktracker.feature.account.users.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.core.ui.BaseLinearLayoutManager
import ru.vktracker.core.ui.OnClickCallback
import ru.vktracker.core.ui.OnThrottleClickListener
import ru.vktracker.databinding.UsersFragmentBinding as Binding
import ru.vktracker.feature.account.users.ui.AccountUserUi
import ru.vktracker.feature.account.users.ui.AccountUsersAdapter
import ru.vktracker.feature.account.users.ui.AccountUsersLoadStateAdapter

/**
 * @author Danil Glazkov on 10.06.2023, 05:44
 */
abstract class BaseAccountUsersFragment<M : BaseAccountUsersViewModel>(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> Binding
) : BaseFragment<Binding, M>(ID, inflate) {

    private val trackingButtonClickCallback = object : OnClickCallback<AccountUserUi> {
        override fun onClick(data: AccountUserUi) = viewModel.changeTrackingStatus(data)
    }

    private val userClickCallback = object : OnClickCallback<AccountUserUi> {
        override fun onClick(data: AccountUserUi) = Unit
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagingAdapter = AccountUsersAdapter(userClickCallback, trackingButtonClickCallback)

        val retryClickListener = OnThrottleClickListener.SingleLonger {
            pagingAdapter.retry()
        }

        val adapterWithLoadState = pagingAdapter.withLoadStateFooter(
            AccountUsersLoadStateAdapter(
                retryClickListener,
                binding.recyclerView
            )
        )

        pagingAdapter.addLoadStateListener { combinedState ->
            val isError = combinedState.refresh is LoadState.Error

            binding.progressLayout.isVisible = combinedState.refresh == LoadState.Loading

            binding.errorLayout.root.isVisible = isError

            binding.recyclerView.isVisible = !isError
            binding.swipeLayout.isVisible = !isError
        }

        binding.errorLayout.tryAgainButton.setOnClickListener(retryClickListener)

        binding.recyclerView.adapter = adapterWithLoadState
        binding.recyclerView.layoutManager = BaseLinearLayoutManager(requireContext())

        viewModel.observeAccountUsers(viewLifecycleOwner) { pagingUsers ->
            pagingAdapter.apply(lifecycleScope, pagingUsers)
        }

        binding.swipeLayout.setOnRefreshListener {
            pagingAdapter.refresh()
            binding.swipeLayout.isRefreshing = false
        }
    }

    companion object {
        private val ID = R.layout.users_fragment
    }
}