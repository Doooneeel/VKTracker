package ru.vktracker.data.account.cache.tracking

/**
 * @author Danil Glazkov on 04.06.2023, 21:58
 */
interface ProvideTrackedUsersDao {
    fun provideTrackedUsersDao(): TrackedUsersDao
}