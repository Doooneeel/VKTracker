package ru.vktracker.feature.users.ui

import androidx.paging.PagingSource.*
import ru.vktracker.R
import ru.vktracker.core.ui.HandleError
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.feature.users.domain.AccountUsersExceptions.*
import java.lang.Exception

/**
 * @author Danil Glazkov on 18.06.2023, 16:14
 */
interface HandleAccountUsersExceptions :  HandleError.Paging<AccountUserUi> {

    class Base(private val resources: ManageResources) : HandleAccountUsersExceptions {

        override fun handle(exception: Exception) = when (exception) {
            is NoFriendsExceptions -> message(R.string.no_friends)
            is NoFavesExceptions -> message(R.string.no_users_in_faves)
            is NoSubscribersExceptions -> message(R.string.no_subscribers)
            else -> LoadResult.Error(exception)
        }

        private fun message(id: Int): LoadResult<Int, AccountUserUi> = LoadResult.Page(
            listOf(AccountUserUi.Message(resources.string(id))), null, null
        )
    }

}