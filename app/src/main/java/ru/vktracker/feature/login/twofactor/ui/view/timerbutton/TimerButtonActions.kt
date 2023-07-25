package ru.vktracker.feature.login.twofactor.ui.view.timerbutton

import ru.vktracker.core.common.Cancel
import ru.vktracker.feature.login.twofactor.ui.view.timerbutton.listener.*

/**
 * @author Danil Glazkov on 09.07.2023, 10:40
 */
interface TimerButtonActions : SetEndOfTimerListener, SetTimeTextFormat, Cancel {
    fun startTimer()
}