package ru.vktracker.feature.account.users.tabs

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor
import ru.vktracker.feature.account.users.ui.AccountUserUi
import ru.vktracker.feature.account.users.ui.AccountUsersCommunication

/**
 * @author Danil Glazkov on 10.06.2023, 02:30
 */
interface BaseAccountUsersViewModel : BaseViewModel, AccountUsersCommunication.Observe {

    fun changeTrackingStatus(user: AccountUserUi)


    abstract class Abstract(
        private val interactor: AccountUsersInteractor,
        private val communication: AccountUsersCommunication,
        dispatchers: CoroutineDispatchers,
    ) : BaseViewModel.Abstract(dispatchers),
        BaseAccountUsersViewModel {

        private val changeTrackingStatusMapper = object : AccountUserUi.Mapper<Unit> {
            override fun map(id: Long, name: String, avatar: String, tracked: Boolean) {
                handle { interactor.changeTrackingStatus(id, tracked) }
            }
        }

        override fun changeTrackingStatus(user: AccountUserUi) { user.map(changeTrackingStatusMapper) }

        override fun observeAccountUsers(
            owner: LifecycleOwner,
            observer: Observer<PagingData<AccountUserUi>>
        ) = communication.observe(owner, observer)
    }

}