package ru.vktracker.core.ui.view.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.vktracker.core.ui.navigation.GenericTabs

/**
 * @author Danil Glazkov on 18.06.2023, 13:46
 */
class GenericFragmentStateAdapter(
    private val tabs: GenericTabs,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        tabs.count()

    override fun createFragment(position: Int): Fragment =
        tabs.fragment(position)
}