package ru.vktracker.feature.account.users.domain

import ru.vktracker.core.common.Page
import ru.vktracker.core.common.Users

/**
 * @author Danil Glazkov on 10.06.2023, 02:59
 */
interface AccountUsersRepository {
    suspend fun accountUsers(page: Page): Users
}