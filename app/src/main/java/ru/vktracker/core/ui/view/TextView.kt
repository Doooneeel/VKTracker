package ru.vktracker.core.ui.view

import android.util.AttributeSet
import android.content.Context
import androidx.appcompat.widget.AppCompatTextView

/**
 * @author Danil Glazkov on 02.06.2023, 12:15
 */
class TextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatTextView(
    context,
    attrs,
    defStyleAttr
) , AbstractView.Text {

    init {
        Skeleton.Base.show(this)
    }

    override fun apply(text: String) {
        setText(text)
        background = null
    }
}