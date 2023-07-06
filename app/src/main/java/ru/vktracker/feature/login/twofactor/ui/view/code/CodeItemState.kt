package ru.vktracker.feature.login.twofactor.ui.view.code

import ru.vktracker.core.common.Compare

/**
 * @author Danil Glazkov on 06.07.2023, 21:12
 */
interface CodeItemState : Compare<CodeItemState> {

    fun symbol(): Char

    fun isActive(): Boolean

    fun cursor(): Boolean


    abstract class Abstract(
        private val symbol: Char,
        private val isActive: Boolean,
        private val cursor: Boolean,
    ) : CodeItemState {

        override fun compare(source: CodeItemState): Boolean = source.cursor() == cursor &&
                source.isActive() == isActive && source.symbol() == symbol

        override fun cursor(): Boolean = cursor

        override fun isActive(): Boolean = isActive

        override fun symbol(): Char = symbol

    }

    object Empty : Abstract(' ', false, false)

    class Complete(symbol: Char) : Abstract(symbol, true, false)

    class Input(symbol: Char, isActive: Boolean) : Abstract(symbol, isActive, isActive)

}