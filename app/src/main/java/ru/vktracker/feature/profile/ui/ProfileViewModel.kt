package ru.vktracker.feature.profile.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.core.ui.Communication
import ru.vktracker.core.ui.Init
import ru.vktracker.core.ui.dialog.AbstractAlertDialog
import ru.vktracker.core.ui.dialog.AlertDialogCommunication
import ru.vktracker.core.ui.navigation.Screen
import ru.vktracker.core.ui.navigation.ScreenCommunication
import ru.vktracker.feature.profile.di.ProfileModule.*
import ru.vktracker.feature.profile.domain.ProfileInteractor
import ru.vktracker.feature.profile.ui.nabigation.ProfileNavigation
import javax.inject.Inject

/**
 * @author Danil Glazkov on 17.06.2023, 06:40
 */
interface ProfileViewModel : BaseViewModel, ProfileUiCommunication.Observe, Init,
    ScreenCommunication.Observe, ProfileNavigation {

    fun observeLogoutDialog(owner: LifecycleOwner, observer: Observer<AbstractAlertDialog>)

    fun showLogoutDialog()


    @HiltViewModel
    class Base @Inject constructor(
        private val interactor: ProfileInteractor,
        @ModuleQualifier
        private val screenCommunication: Communication.Observe<Screen>,
        private val profileNavigation: ProfileNavigation,
        private val communication: ProfileUiCommunication,
        @ModuleQualifier
        private val logoutDialogCommunication: AlertDialogCommunication,
        private val handleDomainRequest: ProfileHandleDomainRequest,
        dispatchers: CoroutineDispatchers,
    ) : BaseViewModel.Abstract(dispatchers),
        ProfileViewModel,
        ProfileNavigation by profileNavigation
    {
        //todo fix
        private var needToShowLogoutDialog = false
        private val onCancel = {
            needToShowLogoutDialog = false
        }

        private val logoutAlertDialog = LogoutAlertDialog(onCancel) {
            /* todo logout */
        }

        override fun init(isFistRun: Boolean) {
            if (isFistRun) handleDomainRequest.handleBlock(viewModelScope) { handler ->
                interactor.fetchProfile(handler)
            }

            if (needToShowLogoutDialog) {
                showLogoutDialog()
            }
        }

        override fun showLogoutDialog() {
            needToShowLogoutDialog = true
            logoutDialogCommunication.put(logoutAlertDialog)
        }

        override fun observeLogoutDialog(
            owner: LifecycleOwner,
            observer: Observer<AbstractAlertDialog>
        ) = logoutDialogCommunication.observe(owner, observer)

        override fun observeProfileUi(owner: LifecycleOwner, observer: Observer<ProfileUi>) =
            communication.observe(owner, observer)

        override fun observeScreen(owner: LifecycleOwner, observer: Observer<Screen>) =
            screenCommunication.observe(owner, observer)
    }

}

