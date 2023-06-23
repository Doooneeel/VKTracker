package ru.vktracker.feature.users.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragment
import ru.vktracker.core.ui.GenericFragmentStateAdapter
import ru.vktracker.databinding.FragmentAccountUsersBinding as Binding

/**
 * @author Danil Glazkov on 09.06.2023, 22:45
 */
@AndroidEntryPoint
class AccountUsersFragment : BaseFragment<Binding>(ID, Binding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabs = AccountUsersTabs(resources)

        binding.viewPager.adapter = GenericFragmentStateAdapter(tabs, this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager, tabs)
            .attach()
    }

    companion object {
        private val ID = R.layout.fragment_account_users
    }
}

