package ru.vktracker.feature.profile.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.common.Logout
import ru.vktracker.core.common.user.User
import ru.vktracker.core.ui.dialog.DialogEvent
import ru.vktracker.core.ui.viewmodel.BaseViewModel
import ru.vktracker.feature.profile.domain.ProfileInteractor
import ru.vktracker.feature.profile.ui.dialog.LogoutDialog
import ru.vktracker.feature.profile.ui.navigation.ProfileNavigation
import ru.vktracker.feature.profile.ui.state.ProfileUiState
import javax.inject.Inject

/**
 * @author Danil Glazkov on 17.06.2023, 06:40
 */
interface ProfileViewModel {

    interface Fragment : BaseViewModel, ProfileNavigation.External, LogoutDialog.Show,
        ProfileCommunications.Observe

    interface Dialog {
        fun logoutDialogEvent(event: DialogEvent<*>)
    }

    interface Combined : Fragment, Dialog


    @HiltViewModel
    class Base @Inject constructor(
        private val interactor: ProfileInteractor,
        private val communications: ProfileCommunications.Combined,
        private val mapperToUi: UserToProfileUiMapper,
        private val navigation: ProfileNavigation.Combined,
        private val logout: Logout,
        dispatchers: CoroutineDispatchers,
    ) : BaseViewModel.Abstract(dispatchers),
        ProfileCommunications.Observe by communications,
        ProfileNavigation.External by navigation,
        Combined
    {
        override fun logoutDialogEvent(event: DialogEvent<*>) {
            if (event is DialogEvent.Confirm) {
                navigation.navigateToWelcome()
                logout.logout()
            }
        }

        override fun showLogoutDialog() = communications.showDialog(
            LogoutDialog.Base()
        )

        override fun firstRunInit() {
            viewModelScope.launchIO {
                interactor.fetchProfile { user: User ->
                    val profileUi: ProfileUi = user.map(mapperToUi)
                    val uiState = ProfileUiState.Initial(profileUi)

                    changeToUi {
                        communications.put(uiState)
                    }
                }
            }
        }
    }
}