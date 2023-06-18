package ru.vktracker.data.account.cloud

import com.vk.sdk.api.users.dto.UsersUserFull
import ru.vktracker.core.common.User
import ru.vktracker.core.common.text.UsernameFormat

/**
 * @author Danil Glazkov on 07.06.2023, 02:28
 */
interface UserCloudToUserMapper {

    fun map(userCloud: UsersUserFull): User


    class Base(private val usernameFormat: UsernameFormat) : UserCloudToUserMapper {

        override fun map(userCloud: UsersUserFull): User = User.Base(
            userCloud.id.value,
            usernameFormat.format(userCloud.firstName, userCloud.lastName),
            userCloud.photo100 ?: ""
        )
    }

}