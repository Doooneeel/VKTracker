package ru.vktracker.core.ui.view.common

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet

/**
 * @author Danil Glazkov on 06.07.2023, 12:14
 */
interface ProvideDeclaredStyle<T> {

    fun style(context: Context): T


    abstract class Abstract<T>(private val attributeSet: AttributeSet) : ProvideDeclaredStyle<T> {

        protected abstract val styleRes: IntArray
        protected abstract fun TypedArray.style(context: Context): T

        override fun style(context: Context): T {
            val typedArray = context.theme.obtainStyledAttributes(attributeSet, styleRes, 0, 0)
            val style = typedArray.style(context)
            typedArray.recycle()

            return style
        }
    }
}