package ru.vktracker.core.ui.view.state

import android.os.Parcelable
import android.view.View
import kotlinx.parcelize.Parcelize

/**
 * @author Danil Glazkov on 11.06.2023, 00:32
 */
interface ViewState : Parcelable {

    fun apply(vararg views: View)

    abstract class AbstractVisibility(private val visibility: Int) : ViewState {
        override fun apply(vararg views: View) = views.forEach { it.visibility = visibility }
    }

    @Parcelize
    object VISIBLE : AbstractVisibility(View.VISIBLE)

    @Parcelize
    object GONE : AbstractVisibility(View.GONE)

    @Parcelize
    object INVISIBLE : AbstractVisibility(View.INVISIBLE)


    abstract class AbstractEnable(private val enabled: Boolean) : ViewState {
        override fun apply(vararg views: View) = views.forEach { it.isEnabled = enabled }
    }

    @Parcelize
    object ENABLE : AbstractEnable(true)

    @Parcelize
    object DISABLE : AbstractEnable(false)

}