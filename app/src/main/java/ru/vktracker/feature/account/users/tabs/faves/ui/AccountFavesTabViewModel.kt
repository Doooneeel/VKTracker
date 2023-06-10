package ru.vktracker.feature.account.users.tabs.faves.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersTabViewModel
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.tabs.AccountUsersHandleDomainRequest
import ru.vktracker.feature.account.users.tabs.faves.di.AccountFavesModule.Companion.COMMUNICATION_NAME
import ru.vktracker.feature.account.users.tabs.faves.di.AccountFavesModule.Companion.HANDLE_REQUEST
import ru.vktracker.feature.account.users.tabs.faves.di.AccountFavesModule.Companion.INTERACTOR_NAME
import javax.inject.Inject
import javax.inject.Named

/**
 * @author Danil Glazkov on 10.06.2023, 01:00
 */
@HiltViewModel
class AccountFavesTabViewModel @Inject constructor(
    @Named(INTERACTOR_NAME)
    private val interactor: AccountUsersInteractor,
    @Named(COMMUNICATION_NAME)
    private val communication: AccountUsersCommunication,
    @Named(HANDLE_REQUEST)
    private val handleDomainRequest: AccountUsersHandleDomainRequest,
    dispatchers: CoroutineDispatchers
) : BaseAccountUsersTabViewModel.Abstract(
    interactor,
    communication,
    handleDomainRequest,
    dispatchers
)