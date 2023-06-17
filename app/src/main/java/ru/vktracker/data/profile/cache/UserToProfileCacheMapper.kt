package ru.vktracker.data.profile.cache

import ru.vktracker.core.common.User

/**
 * @author Danil Glazkov on 17.06.2023, 12:28
 */
class UserToProfileCacheMapper : User.Mapper<ProfileCache> {
    override fun map(id: Long, name: String, avatar: String): ProfileCache =
        ProfileCache(id, name, avatar)
}
