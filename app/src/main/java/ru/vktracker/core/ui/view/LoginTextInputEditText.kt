package ru.vktracker.core.ui.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

/**
 * @author Danil Glazkov on 24.06.2023, 13:54
 */
class LoginTextInputEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputEditText(
    context,
    attrs,
    defStyleAttr
) , AbstractView.Login {
    override fun input(): CharArray =
        (text ?: "").toString().toCharArray()
}