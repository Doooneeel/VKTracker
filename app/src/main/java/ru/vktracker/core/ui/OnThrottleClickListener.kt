package ru.vktracker.core.ui

import android.os.SystemClock
import android.view.View

/**
 * @author Danil Glazkov on 13.06.2023, 10:16
 */
interface OnThrottleClickListener : View.OnClickListener {

    abstract class Abstract(
        private val clickTime: Throttle,
        private val interval: Long,
        private val action: (View) -> Unit
    ) : OnThrottleClickListener {
        override fun onClick(view: View) {
            if (SystemClock.elapsedRealtime() - clickTime.lastTime() > interval) {
                clickTime.update(SystemClock.elapsedRealtime())
                action.invoke(view)
            }
        }
    }

    class SingleSlight(action: (View) -> Unit) : Abstract(Throttle.Base(), SLIGHT, action)
    class SingleMedium(action: (View) -> Unit) : Abstract(Throttle.Base(), MEDIUM, action)
    class SingleLonger(action: (View) -> Unit) : Abstract(Throttle.Base(), LONG, action)


    class Slight(clickTime: Throttle, action: (View) -> Unit) : Abstract(clickTime, SLIGHT, action)
    class Medium(clickTime: Throttle, action: (View) -> Unit) : Abstract(clickTime, MEDIUM, action)
    class Longer(clickTime: Throttle, action: (View) -> Unit) : Abstract(clickTime, LONG, action)

    class Base(throttle: Throttle, delay: Long, action: (View) -> Unit) : Abstract(throttle, delay, action)
    class SingleBase(delay: Long, action: (View) -> Unit) : Abstract(Throttle.Base(), delay, action)


    companion object {
        private const val SLIGHT = 400L
        private const val MEDIUM = 650L
        private const val LONG = 1350L
    }
}


