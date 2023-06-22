package ru.vktracker.core.ui.navigation

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author Danil Glazkov on 10.06.2023, 07:15
 */
interface GenericTabs : TabLayoutMediator.TabConfigurationStrategy {

    fun fragment(position: Int): Fragment

    fun count(): Int


    abstract class Abstract : GenericTabs {

        protected abstract val tabs: List<GenericTab>

        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
            tabs[position].apply(tab)
        }

        override fun fragment(position: Int): Fragment = tabs[position].fragment()

        override fun count(): Int = tabs.size

    }

}