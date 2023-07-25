package ru.vktracker.core.ui.view.common.listeners

import android.view.View
import ru.vktracker.core.ui.view.common.Throttle

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
            val elapsedRealtime = System.currentTimeMillis()

            if (elapsedRealtime - clickTime.lastTime() > interval) {
                clickTime.update(elapsedRealtime)
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


