package ru.vktracker.core.ui

import android.os.SystemClock
import android.view.View

/**
 * @author Danil Glazkov on 13.06.2023, 10:16
 */
interface OnThrottleClickListener : View.OnClickListener {

    abstract class Abstract(
        private val clickTime: ClickTime,
        private val interval: Long,
        private val action: (View) -> Unit
    ) : OnThrottleClickListener  {
        override fun onClick(view: View) {
            if (SystemClock.elapsedRealtime() - clickTime.lastClick() > interval) {
                clickTime.registerClick(SystemClock.elapsedRealtime())
                action.invoke(view)
            }
        }
    }

    class SingleSlight(action: (View) -> Unit) : Abstract(ClickTime.Base(), SLIGHT, action)
    class SingleMedium(action: (View) -> Unit) : Abstract(ClickTime.Base(), MEDIUM, action)
    class SingleLonger(action: (View) -> Unit) : Abstract(ClickTime.Base(), LONG, action)


    class Slight(clickTime: ClickTime, action: (View) -> Unit) : Abstract(clickTime, SLIGHT, action)
    class Medium(clickTime: ClickTime, action: (View) -> Unit) : Abstract(clickTime, MEDIUM, action)
    class Longer(clickTime: ClickTime, action: (View) -> Unit) : Abstract(clickTime, LONG, action)


    companion object {
        private const val SLIGHT = 400L
        private const val MEDIUM = 650L
        private const val LONG = 1350L
    }
}


