package ru.vktracker.feature.account.users.ui

import android.os.Bundle
import android.view.View
import com.vk.api.sdk.VK
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragmentNoViewModel
import ru.vktracker.databinding.FragmentAccountUsersBinding as Binding

/**
 * @author Danil Glazkov on 09.06.2023, 22:45
 */
@AndroidEntryPoint
class AccountUsersFragment : BaseFragmentNoViewModel<Binding>(ID, Binding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //todo make auth
        if (!VK.isLoggedIn()) {
            return
        }

        val tabs = AccountUsersTabs(requireContext().resources)

        binding.viewPager.adapter = UsersAdapter(this, tabs)

        UsersTabLayoutMediator.Base(binding.tabLayout, binding.viewPager, tabs)
            .mediator()
            .attach()
    }

    companion object {
        private val ID = R.layout.fragment_account_users
    }
}

