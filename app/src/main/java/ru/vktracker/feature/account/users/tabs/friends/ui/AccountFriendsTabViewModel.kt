package ru.vktracker.feature.account.users.tabs.friends.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersTabViewModel
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.tabs.AccountUsersHandleDomainRequest
import ru.vktracker.feature.account.users.tabs.friends.di.AccountFriendsModule.Companion.COMMUNICATION_NAME
import ru.vktracker.feature.account.users.tabs.friends.di.AccountFriendsModule.Companion.HANDLE_REQUEST_NAME
import ru.vktracker.feature.account.users.tabs.friends.di.AccountFriendsModule.Companion.INTERACTOR_NAME
import javax.inject.Inject
import javax.inject.Named

/**
 * @author Danil Glazkov on 10.06.2023, 01:56
 */
@HiltViewModel
class AccountFriendsTabViewModel @Inject constructor(
    @Named(INTERACTOR_NAME)
    private val interactor: AccountUsersInteractor,
    @Named(COMMUNICATION_NAME)
    private val communication: AccountUsersCommunication,
    @Named(HANDLE_REQUEST_NAME)
    private val handleDomainRequest: AccountUsersHandleDomainRequest,
    dispatchers: CoroutineDispatchers
) : BaseAccountUsersTabViewModel.Abstract(
    interactor,
    communication,
    handleDomainRequest,
    dispatchers
)