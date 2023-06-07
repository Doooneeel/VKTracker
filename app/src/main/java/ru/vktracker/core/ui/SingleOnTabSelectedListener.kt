package ru.vktracker.core.ui

import com.google.android.material.tabs.TabLayout.*

/**
 * @author Danil Glazkov on 05.06.2023, 22:47
 */
interface SingleOnTabSelectedListener : OnTabSelectedListener {

    abstract class Abstract : SingleOnTabSelectedListener {

        override fun onTabReselected(tab: Tab) = Unit

        override fun onTabSelected(tab: Tab) = Unit

        override fun onTabUnselected(tab: Tab) = Unit

    }

    class Selected(private val block: (Tab) -> Unit) : Abstract() {
        override fun onTabSelected(tab: Tab) = block.invoke(tab)
    }

}