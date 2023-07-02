package ru.vktracker.core.ui.view.fab

import android.os.Parcelable
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.parcelize.Parcelize

/**
 * @author Danil Glazkov on 25.06.2023, 17:08
 */
interface FabViewState : Parcelable {

    fun apply(fab: ExtendedFloatingActionButton)


    abstract class Abstract(private val show: Boolean) : FabViewState {
        override fun apply(fab: ExtendedFloatingActionButton) =
            if (show) fab.show() else fab.hide()
    }

    @Parcelize
    object VISIBLE : Abstract(true)

    @Parcelize
    object INVISIBLE : Abstract(false)

    @Parcelize
    class Base(private val show: Boolean): Abstract(show)

}