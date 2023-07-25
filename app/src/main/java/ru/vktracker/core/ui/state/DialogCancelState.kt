package ru.vktracker.core.ui.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author Danil Glazkov on 15.07.2023, 23:19
 */
interface DialogCancelState : Parcelable {

    fun cancel()

    fun isCancel(): Boolean


    @Parcelize
    class Base(private var isCancel: Boolean = false) : DialogCancelState {

        override fun cancel() { isCancel = true }

        override fun isCancel(): Boolean = isCancel

    }

}