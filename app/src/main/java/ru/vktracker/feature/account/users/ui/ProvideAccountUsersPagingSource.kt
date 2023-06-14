package ru.vktracker.feature.account.users.ui

import androidx.paging.PagingSource
import ru.vktracker.core.ui.paging.ProvidePagingSource
import ru.vktracker.feature.account.users.domain.AccountUsersInteractor

/**
 * @author Danil Glazkov on 12.06.2023, 07:59
 */
class ProvideAccountUsersPagingSource(
    private val interactor: AccountUsersInteractor,
    private val userToUiMapper: UserToUiMapper
) : ProvidePagingSource<AccountUserUi> {
    override fun providePagingSource(): PagingSource<Int, AccountUserUi> =
        AccountUsersPagingSource(interactor, userToUiMapper)
}