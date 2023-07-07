package ru.vktracker.feature.login.twofactor.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.dialog.AbstractDialog
import ru.vktracker.core.ui.dialog.DialogCommunication
import ru.vktracker.core.ui.navigation.Navigation
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.text.MessageCommunication
import ru.vktracker.core.ui.text.Message
import ru.vktracker.core.ui.view.fab.FabViewState
import ru.vktracker.core.ui.view.fab.FabViewStateCommunication
import ru.vktracker.core.ui.view.progress.ProgressCommunication
import ru.vktracker.core.ui.view.progress.ProgressViewState
import ru.vktracker.core.ui.view.state.ViewState
import ru.vktracker.core.ui.view.state.ViewStateCommunication
import ru.vktracker.feature.login.twofactor.ui.view.ResendCodeViewState
import ru.vktracker.feature.login.twofactor.ui.view.ResendCodeViewStateCommunication

/**
 * @author Danil Glazkov on 26.06.2023, 19:50
 */
interface TwoFactorCommunications {

    interface Update : FabViewStateCommunication.Update, ResendCodeViewStateCommunication.Update,
        ProgressCommunication.Update, DialogCommunication.Update
    {
        fun putAboutSendingMessage(text: String)

        fun putConfirmCodeLayoutViewState(viewState: ViewState)
    }

    interface Observe : NavigationCommunication.ObserveChild, FabViewStateCommunication.Observe,
        ResendCodeViewStateCommunication.Observe, ProgressCommunication.Observe,
        DialogCommunication.Observe
    {
        fun observeAboutSendingMessage(owner: LifecycleOwner, observer: Observer<Message>)

        fun observeCodeLayoutViewState(owner: LifecycleOwner, observer: Observer<ViewState>)

    }

    interface Mutable : Update, Observe


    class Base(
        private val navigationCommunication: NavigationCommunication,
        private val aboutSendingMessageCommunication: MessageCommunication,
        private val fabCommunication: FabViewStateCommunication,
        private val confirmCodeLayoutCommunication: ViewStateCommunication,
        private val resendViewStateCommunication: ResendCodeViewStateCommunication,
        private val progressCommunication: ProgressCommunication,
        private val dialogCommunication: DialogCommunication
    ) : Mutable {

        override fun putDialog(dialog: AbstractDialog) = dialogCommunication.put(dialog)

        override fun putProgressViewState(state: ProgressViewState) =
            progressCommunication.put(state)

        override fun putResendCodeViewState(state: ResendCodeViewState) =
            resendViewStateCommunication.put(state)

        override fun putConfirmCodeLayoutViewState(viewState: ViewState) =
            confirmCodeLayoutCommunication.put(viewState)

        override fun putFabViewState(state: FabViewState) = fabCommunication.put(state)

        override fun putAboutSendingMessage(text: String) =
            aboutSendingMessageCommunication.put(Message.Base(text))

        override fun observeCodeLayoutViewState(owner: LifecycleOwner, observer: Observer<ViewState>) =
            confirmCodeLayoutCommunication.observe(owner, observer)

        override fun observeAboutSendingMessage(owner: LifecycleOwner, observer: Observer<Message>) =
            aboutSendingMessageCommunication.observe(owner, observer)

        override fun observeFabViewState(owner: LifecycleOwner, observer: Observer<FabViewState>) =
            fabCommunication.observe(owner, observer)

        override fun observeChildNavigation(owner: LifecycleOwner, observer: Observer<Navigation>) =
            navigationCommunication.observe(owner, observer)

        override fun observeDialog(owner: LifecycleOwner, observer: Observer<AbstractDialog>) =
            dialogCommunication.observe(owner, observer)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<ProgressViewState>) =
            progressCommunication.observe(owner, observer)

        override fun observeResendCodeViewState(
            owner: LifecycleOwner,
            observer: Observer<ResendCodeViewState>
        ) = resendViewStateCommunication.observe(owner, observer)
    }

}