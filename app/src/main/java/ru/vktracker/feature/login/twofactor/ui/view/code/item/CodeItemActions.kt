package ru.vktracker.feature.login.twofactor.ui.view.code.item

/**
 * @author Danil Glazkov on 11.07.2023, 18:15
 */
interface CodeItemActions {

    fun update(state: CodeItemState)

    fun symbol(): Char

}