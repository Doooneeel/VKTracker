package ru.vktracker.core.ui.view.progress

import android.os.Parcelable
import com.google.android.material.progressindicator.BaseProgressIndicator
import kotlinx.parcelize.Parcelize

/**
 * @author Danil Glazkov on 29.06.2023, 00:10
 */
interface ProgressViewState : Parcelable {

    fun apply(progress: BaseProgressIndicator<*>)


    abstract class Abstract(private val show: Boolean) : ProgressViewState {
        override fun apply(progress: BaseProgressIndicator<*>) {
            if (show) progress.show() else progress.hide()
        }
    }

    @Parcelize
    class Base(private val show: Boolean): Abstract(show)

    @Parcelize
    object SHOW : Abstract(true)

    @Parcelize
    object HIDE : Abstract(false)

}