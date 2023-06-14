package ru.vktracker.data.account.cloud

import com.vk.sdk.api.users.dto.UsersUserFull
import ru.vktracker.core.common.User
import ru.vktracker.core.common.Users
import ru.vktracker.core.common.text.UsernameFormat

/**
 * @author Danil Glazkov on 07.06.2023, 02:28
 */
interface UsersCloudToUsersMapper {

    fun map(users: List<UsersUserFull>): Users

    class Base(private val usernameFormat: UsernameFormat) : UsersCloudToUsersMapper {
        override fun map(users: List<UsersUserFull>): Users {
            return Users.Base(users.map { user ->
                User.Base(
                    user.id.value,
                    usernameFormat.format(user.firstName, user.lastName),
                    user.photo100 ?: ""
                )
            })
        }
    }

}