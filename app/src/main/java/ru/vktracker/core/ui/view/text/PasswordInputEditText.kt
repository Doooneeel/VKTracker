package ru.vktracker.core.ui.view.text

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import ru.vktracker.R
import ru.vktracker.core.ui.view.common.AbstractView
import java.util.Arrays

/**
 * @author Danil Glazkov on 28.06.2023, 21:37
 */
class PasswordInputEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : TextInputEditText(
    context,
    attrs,
) , AbstractView.Password {
    private val passwordArray: CharArray

    init {
        context.obtainStyledAttributes(attrs, R.styleable.PasswordInputEditText).run {
            val passwordLength = getInteger(
                R.styleable.PasswordInputEditText_passwordMaxLength,
                DEFAULT_PASSWORD_LENGTH
            )
            passwordArray = CharArray(passwordLength)
            filters += InputFilter.LengthFilter(passwordLength)
            recycle()
        }
    }

    override fun password(): CharArray {
        clear()
        val length: Int = text?.length ?: 0
        text?.getChars(0, length, passwordArray, 0)

        return passwordArray.filterNot { it == Char.MIN_VALUE }.toCharArray()
    }

    override fun clear() = Arrays.fill(passwordArray, Char.MIN_VALUE)

    companion object {
        private const val DEFAULT_PASSWORD_LENGTH = 255
    }

}