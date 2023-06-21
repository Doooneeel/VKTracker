package ru.vktracker.feature.users.ui.friends

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.feature.users.di.AccountFriendsModule.*
import ru.vktracker.feature.users.domain.AccountUsersInteractor
import ru.vktracker.feature.users.ui.AccountUsersCommunication
import ru.vktracker.feature.users.ui.BaseAccountUsersViewModel
import javax.inject.Inject

/**
 * @author Danil Glazkov on 10.06.2023, 01:56
 */
@HiltViewModel
class AccountFriendsViewModel @Inject constructor(
    @ModuleQualifier interactor: AccountUsersInteractor,
    @ModuleQualifier communication: AccountUsersCommunication,
    dispatchers: CoroutineDispatchers
) : BaseAccountUsersViewModel.Abstract(
    interactor,
    communication,
    dispatchers
)