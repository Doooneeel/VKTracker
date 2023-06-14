package ru.vktracker.data.account

import ru.vktracker.data.account.cache.tracking.AccountTrackedUsersCacheDataSource
import ru.vktracker.feature.account.users.domain.AccountTrackedUsersRepository

/**
 * @author Danil Glazkov on 10.06.2023, 23:46
 */
class BaseAccountTrackedUsersRepository(
    private val cacheDataSource: AccountTrackedUsersCacheDataSource
) : AccountTrackedUsersRepository {

    override suspend fun addTrackedUser(id: Long, startTime: Long) =
        cacheDataSource.addTrackedUser(id, startTime)

    override suspend fun deleteTrackedUser(id: Long) =
        cacheDataSource.deleteTrackedUser(id)

    override suspend fun trackedUsersId(): List<Long> = cacheDataSource.trackedUsersId()

    override fun isTracked(id: Long): Boolean = cacheDataSource.exists(id)
}