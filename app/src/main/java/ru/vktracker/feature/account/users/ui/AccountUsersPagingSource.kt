package ru.vktracker.feature.account.users.ui

import ru.vktracker.core.common.Page
import ru.vktracker.core.common.User
import ru.vktracker.core.ui.paging.VkApiPagingSource
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor

/**
 * @author Danil Glazkov on 12.06.2023, 06:34
 */
class AccountUsersPagingSource(
    private val interactor: AccountUsersInteractor,
    private val userToUiMapper: UserToUiMapper,
) : VkApiPagingSource<AccountUserUi>() {

    override suspend fun request(page: Page): List<AccountUserUi> {
        return interactor.users(page).map { user: User ->
            user.map(userToUiMapper)
        }
    }

    override fun handleError(exception: Exception): LoadResult<Int, AccountUserUi> =
        LoadResult.Error(exception)
}