package ru.vktracker.feature.account.users.ui

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.vktracker.core.ui.AbstractTabs

/**
 * @author Danil Glazkov on 10.06.2023, 01:21
 */
interface UsersTabLayoutMediator {

    fun mediator(): TabLayoutMediator

    class Base(
        private val tabLayout: TabLayout,
        private val viewPager: ViewPager2,
        private val tabs: AbstractTabs
    ) : UsersTabLayoutMediator {

        override fun mediator(): TabLayoutMediator {
            return TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabs.tabs()[position].title()
            }
        }
    }

}