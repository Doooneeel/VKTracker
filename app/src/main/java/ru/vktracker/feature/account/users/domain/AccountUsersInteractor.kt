package ru.vktracker.feature.account.users.domain

import android.util.Log
import ru.vktracker.core.common.CurrentTime
import ru.vktracker.core.common.Page
import ru.vktracker.core.common.User

/**
 * @author Danil Glazkov on 10.06.2023, 02:43
 */
interface AccountUsersInteractor : ChangeTrackingStatus {

    suspend fun users(page: Page): List<User>

    class Base(
        private val repository: AccountUsersRepository,
        private val trackedUsersRepository: AccountTrackedUsersRepository,
        private val currentTime: CurrentTime
    ) : AccountUsersInteractor {

        override suspend fun users(page: Page): List<User> {
            Log.d("TTTT", "INTERACTOR")
            return repository.accountUsers(page).asList()
        }

        override suspend fun changeTrackingStatus(id: Long, tracking: Boolean) {
            if (tracking)
                trackedUsersRepository.addTrackedUser(id, currentTime.currentTime())
            else
                trackedUsersRepository.deleteTrackedUser(id)
        }
    }

}