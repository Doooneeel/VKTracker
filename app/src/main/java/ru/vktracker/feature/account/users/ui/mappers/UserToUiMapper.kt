package ru.vktracker.feature.account.users.ui.mappers

import ru.vktracker.core.common.IsTracked
import ru.vktracker.core.common.User
import ru.vktracker.feature.account.users.ui.AccountUserUi

/**
 * @author Danil Glazkov on 02.06.2023, 14:03
 */
interface UserToUiMapper : User.Mapper<AccountUserUi> {

    class Base(private val isTracked: IsTracked) : UserToUiMapper {
        override fun map(id: Long, name: String, avatar: String): AccountUserUi =
            AccountUserUi.Base(id, name, avatar, isTracked.isTracked(id))
    }

}
