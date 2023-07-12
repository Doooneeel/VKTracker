package ru.vktracker.feature.login.twofactor.ui.view.code

import ru.vktracker.core.common.Clear
import ru.vktracker.feature.login.twofactor.ui.view.code.listeners.SetCodeOnChangeListener

/**
 * @author Danil Glazkov on 11.07.2023, 20:49
 */
interface CodeConfirmationViewActions : SetCodeOnChangeListener, Clear {

    fun code(): String

    fun isComplete(): Boolean

}