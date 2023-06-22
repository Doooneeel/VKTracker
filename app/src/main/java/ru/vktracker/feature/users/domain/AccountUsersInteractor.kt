package ru.vktracker.feature.users.domain

import ru.vktracker.core.common.CurrentTime
import ru.vktracker.core.common.Page
import ru.vktracker.core.common.user.User

/**
 * @author Danil Glazkov on 10.06.2023, 02:43
 */
interface AccountUsersInteractor {

    suspend fun users(page: Page): List<User>

    suspend fun changeTrackingStatus(id: Long, tracking: Boolean)


    class Base(
        private val repository: AccountUsersRepository,
        private val trackedUsersRepository: AccountTrackedUsersRepository,
        private val currentTime: CurrentTime
    ) : AccountUsersInteractor {

        override suspend fun users(page: Page): List<User> =
            repository.accountUsers(page)

        override suspend fun changeTrackingStatus(id: Long, tracking: Boolean) {
            if (tracking)
                trackedUsersRepository.addTrackedUser(id, currentTime.currentTime())
            else
                trackedUsersRepository.deleteTrackedUser(id)
        }
    }

}