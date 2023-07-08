package ru.vktracker.feature.login.twofactor.ui.view.timerbutton

import android.content.Context
import android.os.CountDownTimer
import android.os.Parcelable
import android.util.AttributeSet
import com.google.android.material.chip.Chip
import kotlinx.parcelize.Parcelize
import ru.vktracker.core.common.text.TimeTextFormat
import ru.vktracker.core.ui.BaseTimeTextFormat
import ru.vktracker.feature.login.twofactor.ui.view.timerbutton.listener.*
import ru.vktracker.feature.login.twofactor.ui.view.timerbutton.style.ProvideTimerDeclaredStyle
import ru.vktracker.feature.login.twofactor.ui.view.timerbutton.style.TimerDeclaredStyle

/**
 * @author Danil Glazkov on 08.07.2023, 16:24
 */
class ChipTimerButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : Chip (context, attrs),
    SetEndOfTimerListener,
    SetTimeTextFormat
{
    private var timeTextFormat: TimeTextFormat = BaseTimeTextFormat()
    private var listener: EndOfTimerListener = EndOfTimerListener.Unit
    private val sourceText = text.toString()

    private val viewStyle: TimerDeclaredStyle
    private var timer: Timer? = null

    init {
        viewStyle = when (attrs) {
            null -> ProvideTimerDeclaredStyle.Default.style(context)
            else -> ProvideTimerDeclaredStyle.Base(attrs).style(context)
        }
        initTimer(viewStyle.duration.toLong())
    }

    fun startTimer() {
        initTimer(viewStyle.duration.toLong())

        timer?.cancel()
        timer?.start()
    }

    override fun onSaveInstanceState(): Parcelable? {
        val savedState = super.onSaveInstanceState() ?: return null
        return SavedState(
            savedState,
            timer?.millis?.toInt() ?: 0,
            System.currentTimeMillis()
        )
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            val duration = state.duration

            if (duration != 0) {
                val difference = System.currentTimeMillis() - state.currentTime
                initTimer(duration - difference)
                startTimer()
            } else {
                text = viewStyle.endText
            }
        } else {
            super.onRestoreInstanceState(state)

            startTimer()
        }
    }

    override fun setTimeTextFormat(timeTextFormat: TimeTextFormat) {
        this.timeTextFormat = timeTextFormat
    }

    override fun setEndOfTimerListener(listener: EndOfTimerListener) {
        this.listener = listener
    }

    @Parcelize
    private class SavedState(
        val parcelable: Parcelable,
        val duration: Int,
        val currentTime: Long,
    ) : BaseSavedState(parcelable)

    private inner class Timer(
        duration: Long,
        private val interval: Long,
    ) : CountDownTimer(duration, interval) {

        var millis: Long = 0
            private set

        override fun onTick(millisUntilFinished: Long) {
            val endOfTimer = millisUntilFinished < interval

            if (viewStyle.disableWhenCounting) {
                isEnabled = false
            }

            if (endOfTimer) {
                this.millis = 0
                handleEndOfTimer()
            } else {
                this.millis = millisUntilFinished

                text = String.format(
                    viewStyle.startFormattedText,
                    timeTextFormat.format(millisUntilFinished)
                )
            }
        }
        override fun onFinish() = Unit
    }

    private fun handleEndOfTimer() {
        listener.endOfTimer()
        text = viewStyle.endText.ifEmpty { sourceText }

        if (viewStyle.disableWhenCounting) {
            isEnabled = true
        }
    }

    private fun initTimer(duration: Long) {
        if (timer != null) timer!!.cancel()

        timer = Timer(duration, viewStyle.interval.toLong())
    }

}