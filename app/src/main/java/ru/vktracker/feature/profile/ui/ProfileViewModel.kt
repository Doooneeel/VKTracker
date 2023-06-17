package ru.vktracker.feature.profile.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.core.ui.Init
import ru.vktracker.feature.profile.domain.ProfileInteractor
import javax.inject.Inject

/**
 * @author Danil Glazkov on 17.06.2023, 06:40
 */
interface ProfileViewModel : BaseViewModel, ProfileUiCommunication.Observe, Init {

    fun logout()

    @HiltViewModel
    class Base @Inject constructor(
        private val interactor: ProfileInteractor,
        private val communication: ProfileUiCommunication,
        private val handleDomainRequest: ProfileHandleDomainRequest,
        dispatchers: CoroutineDispatchers,
    ) : BaseViewModel.Abstract(dispatchers),
        ProfileViewModel
    {
        override fun init(isFistRun: Boolean) {
            if (isFistRun) handleDomainRequest.handle(viewModelScope) {
                interactor.fetchProfile()
            }
        }

        override fun logout() { /*todo logout */ }

        override fun observeProfileUi(owner: LifecycleOwner, observer: Observer<ProfileUi>) =
            communication.observe(owner, observer)
    }

}