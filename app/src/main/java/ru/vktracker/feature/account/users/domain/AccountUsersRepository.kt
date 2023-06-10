package ru.vktracker.feature.account.users.domain

import ru.vktracker.core.common.IsTracked
import ru.vktracker.core.common.Users

/**
 * @author Danil Glazkov on 10.06.2023, 02:59
 */
interface AccountUsersRepository : ChangeTrackingStatus, IsTracked {
    suspend fun accountUsers(): Users
}