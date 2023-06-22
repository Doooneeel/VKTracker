package ru.vktracker.data.profile

import ru.vktracker.core.common.user.User
import ru.vktracker.data.profile.cache.ProfileCacheDataSource
import ru.vktracker.data.profile.cloud.ProfileCloudDataSource
import ru.vktracker.feature.profile.domain.ProfileRepository

/**
 * @author Danil Glazkov on 17.06.2023, 11:58
 */
class BaseProfileRepository(
    private val cloudDataSource: ProfileCloudDataSource,
    private val cacheDataSource: ProfileCacheDataSource,
) : ProfileRepository {

    override fun cachedProfile(): User = cacheDataSource.readProfile()

    override suspend fun cloudProfile(): User {
        val profile: User = cloudDataSource.requestProfile()
        cacheDataSource.updateProfile(profile)
        return profile
    }

}