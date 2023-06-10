package ru.vktracker.feature.account.users.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.vktracker.core.ui.AbstractTabs

/**
 * @author Danil Glazkov on 09.06.2023, 22:41
 */
class UsersAdapter(fragment: Fragment, private val tabs: AbstractTabs) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = tabs.tabs().size

    override fun createFragment(position: Int): Fragment = tabs.tabs()[position].fragment()

}