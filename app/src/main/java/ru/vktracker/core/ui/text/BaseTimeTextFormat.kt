package ru.vktracker.core.ui.text

import ru.vktracker.core.common.text.TimeTextFormat
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.MINUTES

/**
 * @author Danil Glazkov on 07.07.2023, 14:41
 */
class BaseTimeTextFormat : TimeTextFormat {
    override fun format(millis: Long): String {
        val minutes = MILLISECONDS.toMinutes(millis)
        val seconds = MILLISECONDS.toSeconds(millis) -
                MINUTES.toSeconds(MILLISECONDS.toMinutes(millis))

        return String.format("%02d:%02d", minutes, seconds)
    }
}