package ru.vktracker.feature.users.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseViewModel
import ru.vktracker.feature.users.domain.AccountUsersInteractor

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
        BaseAccountUsersViewModel
    {
        private val changeTrackingStatus = object : AccountUserUi.Mapper<Unit> {
            override fun map(id: Long, name: String, avatar: String, tracked: Boolean) {
                handle { interactor.changeTrackingStatus(id, tracked) }
            }
        }

        override fun changeTrackingStatus(user: AccountUserUi) { user.map(changeTrackingStatus) }

        override fun observeAccountUsers(
            owner: LifecycleOwner,
            observer: Observer<PagingData<AccountUserUi>>
        ) = communication.observe(owner, observer)
    }

}