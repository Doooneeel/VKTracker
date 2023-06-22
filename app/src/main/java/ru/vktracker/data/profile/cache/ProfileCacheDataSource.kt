package ru.vktracker.data.profile.cache

import ru.vktracker.core.common.user.User
import ru.vktracker.core.common.exception.EmptyCacheException
import ru.vktracker.data.core.cache.UserCache

/**
 * @author Danil Glazkov on 17.06.2023, 12:15
 */
interface ProfileCacheDataSource {

    fun readProfile(): User

    fun updateProfile(profile: User)


    class Base(
        private val dataStore: ProfilePreferencesDataStore,
        private val cacheToCommonMapper: UserCache.Mapper<User>,
        private val commonToCacheMapper: User.Mapper<UserCache>,
    ) : ProfileCacheDataSource {

        override fun readProfile(): User {
            val profile: UserCache = dataStore.read(PROFILE_KEY, UserCache(0, "", ""))

            return if (profile.id == 0L)
                throw EmptyCacheException()
            else
                profile.map(cacheToCommonMapper)
        }

        override fun updateProfile(profile: User) {
            val profileCache: UserCache = profile.map(commonToCacheMapper)
            dataStore.save(PROFILE_KEY, profileCache)
        }

        companion object {
            private const val PROFILE_KEY = "profileCacheDataSourceKey"
        }
    }

}