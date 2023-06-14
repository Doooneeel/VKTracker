package ru.vktracker.core.ui

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller

/**
 * @author Danil Glazkov on 13.06.2023, 12:52
 */
class BaseLinearSmoothScroller(
    context: Context,
    private val millisecondsPerInch: Float = 25F
) : LinearSmoothScroller(context) {
    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float =
        millisecondsPerInch / displayMetrics.densityDpi
}