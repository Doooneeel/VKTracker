package ru.vktracker.feature.users.domain

/**
 * @author Danil Glazkov on 18.06.2023, 16:11
 */
abstract class AccountUsersExceptions : Exception() {

    class NoFriendsExceptions : AccountUsersExceptions()

    class NoFavesExceptions : AccountUsersExceptions()

    class NoSubscribersExceptions : AccountUsersExceptions()

}