package ru.vktracker.core.ui

import androidx.fragment.app.Fragment

/**
 * @author Danil Glazkov on 10.06.2023, 07:15
 */
interface AbstractTab {

    fun title(): String

    fun fragment(): Fragment

    class Base(
        private val fragment: Fragment,
        private val title: String
    ) : AbstractTab {
        override fun fragment(): Fragment = fragment
        override fun title(): String = title
    }
}

