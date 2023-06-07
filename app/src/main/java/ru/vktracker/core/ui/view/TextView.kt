package ru.vktracker.core.ui.view

import android.util.AttributeSet
import android.content.Context
import com.google.android.material.textview.MaterialTextView

/**
 * @author Danil Glazkov on 02.06.2023, 12:15
 */
class TextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialTextView(
    context,
    attrs
) , AbstractView.Text {
    override fun apply(text: String) = setText(text)
}