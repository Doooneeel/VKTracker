package ru.vktracker.feature.login.twofactor.ui.view.timerbutton.listener

/**
 * @author Danil Glazkov on 08.07.2023, 18:33
 */
fun interface EndOfTimerListener {

    fun endOfTimer()


    object Unit : EndOfTimerListener {
        override fun endOfTimer() = kotlin.Unit
    }

}