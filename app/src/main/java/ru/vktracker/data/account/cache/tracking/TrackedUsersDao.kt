package ru.vktracker.data.account.cache.tracking

import androidx.room.*

/**
 * @author Danil Glazkov on 04.06.2023, 21:56
 */
@Dao
interface TrackedUsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: TrackedUserCache)

    @Query("DELETE FROM tracked_users WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM tracked_users")
    suspend fun allTrackedUsers(): List<TrackedUserCache>

    @Query("SELECT EXISTS(SELECT * FROM tracked_users WHERE id = :id)")
    fun exist(id: Long): Boolean

}