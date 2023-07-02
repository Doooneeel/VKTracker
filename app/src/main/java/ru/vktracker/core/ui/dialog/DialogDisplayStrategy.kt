package ru.vktracker.core.ui.dialog

/**
 * @author Danil Glazkov on 30.06.2023, 8:34
 */
interface DialogDisplayStrategy {

    fun needToShow(): Boolean

    fun dialogCanceled()
    fun dialogDismissed()
    fun dialogShown()


    class UntilCancel : DialogDisplayStrategy {

        private var needToShow = true
        private var dialogClosed = false

        override fun needToShow(): Boolean = needToShow
        override fun dialogCanceled() { dialogClosed = true }
        override fun dialogDismissed() { needToShow = !dialogClosed }
        override fun dialogShown() = Unit

    }

    class Once : DialogDisplayStrategy {

        private var needToShow = true

        override fun needToShow(): Boolean = needToShow
        override fun dialogDismissed() = Unit
        override fun dialogCanceled() = Unit
        override fun dialogShown() { needToShow = false }
    }

}