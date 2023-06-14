package ru.vktracker.data.core

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vktracker.data.account.cache.tracking.ProvideTrackedUsersDao
import ru.vktracker.data.account.cache.tracking.TrackedUserCache

/**
 * @author Danil Glazkov on 10.06.2023, 09:44
 */
@Database(
    entities =
    [
        TrackedUserCache::class
    ],
    exportSchema = false,
    version = 1
)
abstract class MainDatabase : RoomDatabase(), ProvideTrackedUsersDao