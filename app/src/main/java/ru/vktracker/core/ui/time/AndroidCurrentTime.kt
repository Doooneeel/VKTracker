package ru.vktracker.core.ui.time

import android.icu.util.Calendar
import ru.vktracker.core.common.CurrentTime

/**
 * @author Danil Glazkov on 07.06.2023, 02:41
 */
class AndroidCurrentTime : CurrentTime {
    override fun currentTime(): Long = Calendar.getInstance().timeInMillis
}