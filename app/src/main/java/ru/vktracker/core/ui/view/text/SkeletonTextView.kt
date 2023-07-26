package ru.vktracker.core.ui.view.text

import android.util.AttributeSet
import android.content.Context
import com.google.android.material.textview.MaterialTextView
import ru.vktracker.core.ui.view.common.AbstractView
import ru.vktracker.core.ui.view.common.skeleton.ProvideSkeletonDeclaredStyle
import ru.vktracker.core.ui.view.common.skeleton.Skeleton

/**
 * @author Danil Glazkov on 02.06.2023, 12:15
 */
class SkeletonTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : MaterialTextView(
    context,
    attrs,
) , AbstractView.Text {

    private val skeleton = Skeleton.Base(this)

    private val declaredStyle = when (attrs) {
        null -> ProvideSkeletonDeclaredStyle.Default()
        else -> ProvideSkeletonDeclaredStyle.Base(attrs)
    }.style(context)

    init {
        if (declaredStyle.enabled) skeleton.show()
    }

    override fun apply(text: String) {
        if (declaredStyle.enabled) skeleton.hide()
        setText(text)
    }
}