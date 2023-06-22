package ru.vktracker.data.core.cache

import ru.vktracker.core.common.user.User

/**
 * @author Danil Glazkov on 20.06.2023, 19:04
 */
class UserToCacheMapper : User.Mapper<UserCache> {
    override fun map(id: Long, name: String, avatar: String) = UserCache(id, name, avatar)
}