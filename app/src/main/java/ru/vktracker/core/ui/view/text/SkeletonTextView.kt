package ru.vktracker.core.ui.view.text

import android.util.AttributeSet
import android.content.Context
import com.google.android.material.textview.MaterialTextView
import ru.vktracker.R
import ru.vktracker.core.ui.view.common.AbstractView
import ru.vktracker.core.ui.view.common.Skeleton

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
    private val enableSkeleton: Boolean

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkeletonTextView)
        enableSkeleton = typedArray.getBoolean(R.styleable.SkeletonTextView_enableSkeleton, false)

        if (enableSkeleton) skeleton.show()
        typedArray.recycle()
    }

    override fun apply(text: String) {
        if (enableSkeleton) skeleton.hide()
        setText(text)
    }
}