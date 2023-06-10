package ru.vktracker.feature.account.users.tabs

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.ui.AccountUserUi
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication
import ru.vktracker.feature.account.users.ui.AccountUsersUi

/**
 * @author Danil Glazkov on 10.06.2023, 02:30
 */
interface BaseAccountUsersTabViewModel : BaseViewModel, AccountUsersCommunication.Observe {

    fun changeTrackingStatus(user: AccountUserUi)

    fun fetchUsers()

    abstract class Abstract(
        private val interactor: AccountUsersInteractor,
        private val communication: AccountUsersCommunication,
        private val handleRequest: AccountUsersHandleDomainRequest,
        dispatchers: CoroutineDispatchers
    ) : BaseViewModel.Abstract(dispatchers),
        BaseAccountUsersTabViewModel
    {
        private val changeTrackingStatusMapper = object : AccountUserUi.Mapper<Unit> {
            override fun map(id: Long, name: String, avatar: String, tracked: Boolean) {
                interactor.changeTrackingStatus(id, tracked)
            }
        }

        override fun fetchUsers() {
            handleRequest.handle(viewModelScope) {
                interactor.users()
            }
        }

        override fun changeTrackingStatus(user: AccountUserUi) = user.map(changeTrackingStatusMapper)

        override fun observeAccountUsers(owner: LifecycleOwner, observer: Observer<AccountUsersUi>) =
            communication.observe(owner, observer)
    }

}