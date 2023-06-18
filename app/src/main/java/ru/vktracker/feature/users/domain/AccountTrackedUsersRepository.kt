package ru.vktracker.feature.users.domain

import ru.vktracker.core.common.IsTracked

/**
 * @author Danil Glazkov on 10.06.2023, 22:46
 */
interface AccountTrackedUsersRepository : IsTracked {

    suspend fun addTrackedUser(id: Long, startTime: Long)

    suspend fun deleteTrackedUser(id: Long)

    suspend fun trackedUsersId(): List<Long>

}