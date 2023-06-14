package ru.vktracker.core.ui

import android.view.View

/**
 * @author Danil Glazkov on 11.06.2023, 00:32
 */
interface Visibility {

    fun apply(view: View)

    abstract class Abstract(private val visibility: Int) : Visibility {
        override fun apply(view: View) { view.visibility = visibility }
    }

    object Visible : Abstract(View.VISIBLE)
    object Gone : Abstract(View.GONE)
    object InVisible : Abstract(View.INVISIBLE)

}