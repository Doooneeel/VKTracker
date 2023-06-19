package ru.vktracker.core.ui.navigation

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout

/**
 * @author Danil Glazkov on 18.06.2023, 23:31
 */
interface GenericTab {

    fun apply(tab: TabLayout.Tab)

    fun fragment(): Fragment


    class Base(
        private val fragment: Fragment,
        private val title: String,
    ) : GenericTab {

        override fun apply(tab: TabLayout.Tab) { tab.text = title }

        override fun fragment(): Fragment = fragment

    }

}