package ru.vktracker.feature.login.twofactor.ui.view

import android.widget.TextView
import kotlinx.parcelize.Parcelize
import ru.vktracker.R
import ru.vktracker.core.ui.view.state.TextViewState

/**
 * @author Danil Glazkov on 07.07.2023, 16:48
 */
interface ResendCodeViewState : TextViewState {

    abstract class Abstract(
        private val time: String,
        private val enabled: Boolean,
    ) : ResendCodeViewState {
        override fun apply(view: TextView) {
            view.text = view.resources.getString(R.string.resend_two_fa_code, time)
            view.isEnabled = enabled
        }
    }

    @Parcelize
    object EmptyTimeEnable : Abstract("", true)

    @Parcelize
    object EmptyTimeDisable : Abstract("", false)

    @Parcelize
    class TimeDisable(private val time: String) : Abstract(time, false)

}