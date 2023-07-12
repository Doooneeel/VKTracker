package ru.vktracker.feature.login.twofactor.ui.view.code.item

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Paint
import ru.vktracker.feature.login.twofactor.ui.view.code.style.CodeItemDeclaredStyle

/**
 * @author Danil Glazkov on 06.07.2023, 21:12
 */
interface CodeItemState {

    fun symbol(cursor: Char): Char

    fun borderAnimator(paint: Paint, style: CodeItemDeclaredStyle): ObjectAnimator?


    abstract class Abstract(
        private val symbol: Char,
        private val showCursor: Boolean,
    ) : CodeItemState {

        protected abstract fun fromColor(style: CodeItemDeclaredStyle): Int
        protected abstract fun toColor(style: CodeItemDeclaredStyle): Int

        override fun borderAnimator(paint: Paint, style: CodeItemDeclaredStyle): ObjectAnimator? {
            val borderColor = style.borderColor
            val borderColorActive = style.borderColorActive

            if (borderColor == borderColorActive) return null

            return ObjectAnimator.ofObject(paint, PROPERTY, ArgbEvaluator(),
                fromColor(style), toColor(style)
            )
        }

        override fun symbol(cursor: Char): Char = if (showCursor) cursor else symbol

        companion object {
            private const val PROPERTY = "color"
        }
    }


    object Empty : CodeItemState {
        override fun symbol(cursor: Char): Char = ' '
        override fun borderAnimator(paint: Paint, style: CodeItemDeclaredStyle) = null
    }


    abstract class AbstractComplete(symbol: Char) : Abstract(symbol, false) {
        override fun fromColor(style: CodeItemDeclaredStyle) = style.borderColor
        override fun toColor(style: CodeItemDeclaredStyle) = style.borderColorActive
    }

    data class Complete(private val symbol: Char) : AbstractComplete(symbol)

    data class CompleteLastItem(private val symbol: Char) : AbstractComplete(symbol) {
        override fun fromColor(style: CodeItemDeclaredStyle) = style.borderColorActive
    }


    abstract class AbstractInput(
        symbol: Char,
        private val isActive: Boolean,
    ) : Abstract(symbol, isActive) {
        override fun fromColor(style: CodeItemDeclaredStyle): Int =
            if (isActive) style.borderColor else style.borderColorActive

        override fun toColor(style: CodeItemDeclaredStyle): Int =
            if (isActive) style.borderColorActive else style.borderColor
    }


    data class InputLastItem(
        private val symbol: Char,
        private val isActive: Boolean,
    ) : AbstractInput(symbol, isActive) {
        override fun fromColor(style: CodeItemDeclaredStyle): Int =
            style.borderColorActive
    }


    data class Input(
        private val symbol: Char,
        private val isActive: Boolean,
        private val hasFocus: Boolean
    ) : AbstractInput(symbol, isActive) {

        override fun fromColor(style: CodeItemDeclaredStyle): Int {
            return if (isActive) {
                //fixme
                if (hasFocus) {
                    style.borderColorActive
                } else {
                    style.borderColor
                }
            } else {
                if (hasFocus) {
                    style.borderColor
                } else {
                    style.borderColor
                }
            }
        }

        override fun toColor(style: CodeItemDeclaredStyle): Int {
            if (!hasFocus) return style.borderColor

            return if (isActive) style.borderColorActive else style.borderColor
        }
    }
}