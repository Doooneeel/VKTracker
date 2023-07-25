package ru.vktracker.feature.login.twofactor.ui.view.timerbutton

import android.content.Context
import android.os.Parcelable
import android.os.SystemClock
import android.util.AttributeSet
import com.google.android.material.chip.Chip
import ru.vktracker.core.common.text.TimeTextFormat
import ru.vktracker.core.ui.text.BaseTimeTextFormat
import ru.vktracker.feature.login.twofactor.ui.view.timerbutton.listener.*
import ru.vktracker.feature.login.twofactor.ui.view.timerbutton.style.ProvideTimerDeclaredStyle
import ru.vktracker.feature.login.twofactor.ui.view.timerbutton.style.TimerDeclaredStyle

/**
 * @author Danil Glazkov on 08.07.2023, 16:24
 */
class TimerChipView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : Chip (context, attributeSet), TimerButtonActions {

    private var timeFormat: TimeTextFormat = BaseTimeTextFormat()
    private var listener: EndOfTimerListener = EndOfTimerListener.Unit

    private val declaration: TimerDeclaredStyle = when (attributeSet) {
        null -> ProvideTimerDeclaredStyle.Default.style(context)
        else -> ProvideTimerDeclaredStyle.Base(attributeSet).style(context)
    }

    private var currentViewState: TimerViewState = TimerViewState.Init
    private var countingState: TimerViewState = TimerViewState.Init
    private val cancelState: TimerViewState = TimerViewState.Canceled(declaration) {
        listener.endOfTimer()
    }

    override fun startTimer() {
        setupCountdownState(declaration.duration)
        currentViewState = countingState
        currentViewState.apply(this)
    }

    override fun cancel() {
        currentViewState = cancelState
        currentViewState.apply(this)
    }

    override fun setTimeTextFormat(textFormat: TimeTextFormat) {
        this.timeFormat = textFormat
    }

    override fun setEndOfTimerListener(listener: EndOfTimerListener) {
        this.listener = listener
    }

    override fun onSaveInstanceState(): Parcelable {
        val currentTime = SystemClock.elapsedRealtime()
        currentViewState.cancel()

        return TimerViewSavedState(
            super.onSaveInstanceState(),
            currentViewState.millisUntilFinished(),
            currentTime
        )
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        currentViewState = if (state is TimerViewSavedState) {
            super.onRestoreInstanceState(state.superState)

            val millis = state.millisUntilFinished
            val duration = (millis + state.currentTime) - SystemClock.elapsedRealtime()
            when {
                duration >= declaration.interval -> {
                    setupCountdownState(duration)
                    countingState
                }
                millis == -1L -> TimerViewState.Init
                else -> cancelState
            }
        } else {
            super.onRestoreInstanceState(state)
            TimerViewState.Init
        }
        currentViewState.apply(this)
    }

    private fun setupCountdownState(duration: Long) {
        countingState.cancel()
        countingState = TimerViewState.Countdown(declaration, timeFormat, duration, endOfTimer = {
            currentViewState = cancelState
            currentViewState.apply(this)
        })
    }
}