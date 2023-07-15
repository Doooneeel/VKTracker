package ru.vktracker.feature.login.twofactor.ui.view.code.item

import ru.vktracker.feature.login.twofactor.ui.view.code.style.CodeItemDeclaredStyle

/**
 * @author Danil Glazkov on 06.07.2023, 21:12
 */
interface CodeItemState {

    fun symbol(cursor: Char): Char

    fun borderColor(style: CodeItemDeclaredStyle): Int


    abstract class Abstract(
        private val symbol: Char,
        private val isActive: Boolean,
        private val showCursor: Boolean,
    ) : CodeItemState {

        override fun borderColor(style: CodeItemDeclaredStyle): Int {
            val borderColor = style.borderColor
            val borderColorActive = style.borderColorActive

            if (borderColor == borderColorActive) return borderColor

            return if (isActive) style.borderColorActive else style.borderColor
        }

        override fun symbol(cursor: Char): Char = if (showCursor) cursor else symbol
    }


    object Empty : CodeItemState {
        override fun symbol(cursor: Char): Char = ' '
        override fun borderColor(style: CodeItemDeclaredStyle) = style.borderColor
    }

    data class Complete(
        private val symbol: Char,
        private val hasFocus: Boolean
    ) : Abstract(symbol, hasFocus, false)

    data class Input(
        private val symbol: Char,
        private val isActive: Boolean,
        private val showCursor: Boolean,
    ) : Abstract(symbol, isActive, showCursor)
}