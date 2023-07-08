package ru.vktracker.feature.login.twofactor.ui.view.timerbutton.style

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import ru.vktracker.R
import ru.vktracker.core.ui.view.common.ProvideDeclaredStyle

/**
 * @author Danil Glazkov on 08.07.2023, 16:32
 */
interface ProvideTimerDeclaredStyle : ProvideDeclaredStyle<TimerDeclaredStyle> {

    object Default : ProvideTimerDeclaredStyle {
        private val style = TimerDeclaredStyle(
            duration = 30_000,
            interval = 1000,
            startFormattedText = "",
            endText = "",
            disableWhenCounting = false
        )

        override fun style(context: Context) = style
    }

    class Base(
        attributeSet: AttributeSet,
    ) : ProvideDeclaredStyle.Abstract<TimerDeclaredStyle>(attributeSet),
        ProvideTimerDeclaredStyle {
        override val styleRes: IntArray = R.styleable.TimerButton

        override fun TypedArray.style(context: Context): TimerDeclaredStyle {
            val default = Default.style(context)

            val duration = getInteger(R.styleable.TimerButton_timerDuration, default.duration)
            val interval = getInteger(R.styleable.TimerButton_timerInterval, default.interval)
            val endText = getString(R.styleable.TimerButton_timerEndText) ?: default.endText
            val disableWhenCounting = getBoolean(R.styleable.TimerButton_timerDisableWhenCounting,
                default.disableWhenCounting
            )
            val startFormattedText = getString(R.styleable.TimerButton_timerStartFormattedText) ?:
                default.startFormattedText

            return TimerDeclaredStyle(
                duration = duration,
                interval = interval,
                endText = endText,
                disableWhenCounting = disableWhenCounting,
                startFormattedText = startFormattedText
            )
        }
    }

}