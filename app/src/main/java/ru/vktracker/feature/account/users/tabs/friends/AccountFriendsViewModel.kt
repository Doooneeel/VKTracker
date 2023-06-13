package ru.vktracker.feature.account.users.tabs.friends

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersViewModel
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.tabs.friends.AccountFriendsModule.*
import javax.inject.Inject

/**
 * @author Danil Glazkov on 10.06.2023, 01:56
 */
@HiltViewModel
@ModuleQualifier
class AccountFriendsViewModel @Inject constructor(
    @ModuleQualifier interactor: AccountUsersInteractor,
    @ModuleQualifier communication: AccountUsersCommunication,
    dispatchers: CoroutineDispatchers
) : BaseAccountUsersViewModel.Abstract(
    interactor,
    communication,
    dispatchers
)