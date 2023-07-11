package ru.vktracker.feature.login.twofactor.ui.view.timerbutton.style

/**
 * @author Danil Glazkov on 08.07.2023, 16:31
 */
class TimerDeclaredStyle(
    val duration: Long,
    val interval: Long,
    val disableWhenCounting: Boolean,
    val startFormattedText: String,
    val endText: String,
)