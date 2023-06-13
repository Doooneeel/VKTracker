package ru.vktracker.feature.account.users.tabs.faves

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.tabs.BaseAccountUsersViewModel
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.tabs.faves.AccountFavesModule.ModuleQualifier
import javax.inject.Inject

/**
 * @author Danil Glazkov on 10.06.2023, 01:00
 */
@HiltViewModel
@ModuleQualifier
class AccountFavesViewModel @Inject constructor(
    @ModuleQualifier interactor: AccountUsersInteractor,
    @ModuleQualifier communication: AccountUsersCommunication,
    dispatchers: CoroutineDispatchers
) : BaseAccountUsersViewModel.Abstract(
    interactor,
    communication,
    dispatchers
)