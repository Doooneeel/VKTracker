package ru.vktracker.core.ui.view.chip

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.chip.Chip
import ru.vktracker.core.ui.view.common.AbstractView

/**
 * @author Danil Glazkov on 02.06.2023, 13:41
 */
class ChipView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : Chip(context, attrs), AbstractView.ToggleButton {
    override fun apply(toggle: Boolean) { isChecked = toggle }
}