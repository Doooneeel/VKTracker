package ru.vktracker.core.ui.view.text

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import ru.vktracker.core.ui.view.common.AbstractView

/**
 * @author Danil Glazkov on 26.06.2023, 23:34
 */
class InputEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : TextInputEditText(
    context,
    attrs,
) , AbstractView.Input {
    override fun input() = (text ?: "").toString()
}