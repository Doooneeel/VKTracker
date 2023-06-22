package ru.vktracker.data.core.cache

import ru.vktracker.core.common.user.User

/**
 * @author Danil Glazkov on 20.06.2023, 19:40
 */
class UserCacheToCommonMapper : UserCache.Mapper<User> {
    override fun map(id: Long, name: String, avatar: String): User = User.Base(id, name, avatar)
}