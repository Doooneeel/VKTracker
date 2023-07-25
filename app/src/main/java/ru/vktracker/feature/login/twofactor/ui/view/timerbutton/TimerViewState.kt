package ru.vktracker.feature.login.twofactor.ui.view.timerbutton

import android.os.CountDownTimer
import android.widget.TextView
import ru.vktracker.core.common.Cancel
import ru.vktracker.core.common.text.TimeTextFormat
import ru.vktracker.feature.login.twofactor.ui.view.timerbutton.style.TimerDeclaredStyle

/**
 * @author Danil Glazkov on 09.07.2023, 11:15
 */
interface TimerViewState : Cancel {

    fun apply(view: TextView)

    fun millisUntilFinished(): Long


    object Init : TimerViewState {
        override fun apply(view: TextView) = Unit
        override fun millisUntilFinished() = -1L
        override fun cancel() = Unit
    }

    class Canceled(
        private val viewStyle: TimerDeclaredStyle,
        private val block: () -> Unit,
    ) : TimerViewState {

        override fun millisUntilFinished() = 0L
        override fun cancel() = Unit

        override fun apply(view: TextView) {
            block.invoke()

            if (viewStyle.disableWhenCounting) {
                view.isEnabled = true
            }
            view.text = viewStyle.endText
        }
    }

    class Countdown(
        private val viewStyle: TimerDeclaredStyle,
        private val timeTextFormat: TimeTextFormat,
        private val duration: Long,
        private val endOfTimer: () -> Unit,
    ) : TimerViewState {
        private val interval = viewStyle.interval
        private var timer: CountDownTimer? = null
        private var millis: Long = 0L

        override fun apply(view: TextView) {
            cancel()

            if (viewStyle.disableWhenCounting) {
                view.isEnabled = false
            }
            timer = object : CountDownTimer(duration, interval) {
                override fun onFinish() = Unit

                override fun onTick(millisUntilFinished: Long) {
                    if (millisUntilFinished < interval) {
                        millis = 0
                        endOfTimer.invoke()
                    } else {
                        millis = millisUntilFinished

                        view.text = String.format(
                            viewStyle.startFormattedText,
                            timeTextFormat.format(millisUntilFinished)
                        )
                    }
                }
            }
            timer!!.start()
        }

        override fun millisUntilFinished() = millis

        override fun cancel() {
            timer?.cancel()
        }
    }

}