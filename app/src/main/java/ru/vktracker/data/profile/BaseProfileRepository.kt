package ru.vktracker.data.profile

import ru.vktracker.core.common.User
import ru.vktracker.data.profile.cache.ProfileCache
import ru.vktracker.data.profile.cache.ProfileCacheDataSource
import ru.vktracker.data.profile.cloud.ProfileCloudDataSource
import ru.vktracker.feature.profile.domain.ProfileRepository

/**
 * @author Danil Glazkov on 17.06.2023, 11:58
 */
class BaseProfileRepository(
    private val cloudDataSource: ProfileCloudDataSource,
    private val cacheDataSource: ProfileCacheDataSource,
    private val mapperToCache: User.Mapper<ProfileCache>,
) : ProfileRepository {
    override suspend fun loadProfile(): User {
        return runCatching {
            val profile = cloudDataSource.requestProfile()
            cacheDataSource.save(profile.map(mapperToCache))
            profile
        }.getOrElse {
            cacheDataSource.read().run {
                User.Base(id, name, avatar)
            }
        }
    }
}