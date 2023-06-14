package ru.vktracker.data.account.cache.tracking

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * @author Danil Glazkov on 07.06.2023, 02:45
 */
interface AccountTrackedUsersCacheDataSource {

    suspend fun trackedUsersId(): List<Long>

    suspend fun addTrackedUser(id: Long, time: Long)

    suspend fun deleteTrackedUser(id: Long)

    fun exists(id: Long): Boolean


    class Base(private val dao: TrackedUsersDao) : AccountTrackedUsersCacheDataSource {

        private val mutex = Mutex()


        override suspend fun deleteTrackedUser(id: Long) = mutex.withLock {
            dao.delete(id)
        }

        override fun exists(id: Long): Boolean = dao.exist(id)

        override suspend fun addTrackedUser(id: Long, time: Long) = mutex.withLock {
            dao.insert(TrackedUserCache(id, time))
        }

        override suspend fun trackedUsersId(): List<Long> = mutex.withLock {
            dao.allTrackedUsers().map { it.id }
        }
    }

}