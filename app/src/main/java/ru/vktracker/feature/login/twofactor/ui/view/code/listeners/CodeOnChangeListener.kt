package ru.vktracker.feature.login.twofactor.ui.view.code.listeners

/**
 * @author Danil Glazkov on 06.07.2023, 10:30
 */
fun interface CodeOnChangeListener {

    fun onCodeChange(code: String, isComplete: Boolean)


    object Unit : CodeOnChangeListener {
        override fun onCodeChange(code: String, isComplete: Boolean) = kotlin.Unit
    }

}