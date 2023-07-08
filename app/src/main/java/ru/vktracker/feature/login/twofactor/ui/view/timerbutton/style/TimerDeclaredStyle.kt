package ru.vktracker.feature.login.twofactor.ui.view.timerbutton.style

/**
 * @author Danil Glazkov on 08.07.2023, 16:31
 */
class TimerDeclaredStyle(
    val duration: Int,
    val interval: Int,
    val disableWhenCounting: Boolean,
    val startFormattedText: String,
    val endText: String,
)