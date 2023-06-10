package ru.vktracker.feature.account.users.ui.mappers

import ru.vktracker.core.common.User
import ru.vktracker.core.common.Users
import ru.vktracker.feature.account.users.ui.AccountUsersUi

/**
 * @author Danil Glazkov on 02.06.2023, 13:53
 */
interface UsersToUiMapper : Users.Mapper<AccountUsersUi> {

    //todo make grouping and sorting
    class Base(private val userToUiMapper: UserToUiMapper) : UsersToUiMapper {
        override fun map(users: List<User>): AccountUsersUi =
            AccountUsersUi.Base(users.map { it.map(userToUiMapper) })
    }


    //todo make grouping, sorting and group of important
    class Friends(
        private val userToUiMapper: UserToUiMapper
    ) : UsersToUiMapper {
        override fun map(users: List<User>): AccountUsersUi {
            return AccountUsersUi.Base(users.map { it.map(userToUiMapper) })
        }
    }

}