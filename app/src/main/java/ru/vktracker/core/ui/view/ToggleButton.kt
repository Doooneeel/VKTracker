package ru.vktracker.core.ui.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.chip.Chip

/**
 * @author Danil Glazkov on 02.06.2023, 13:41
 */
class ToggleButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : Chip(context, attrs),
    AbstractView.ToggleButton
{
    override fun apply(toggle: Boolean) { isChecked = toggle }
}