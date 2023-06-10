package ru.vktracker.feature.account.users.domain

/**
 * @author Danil Glazkov on 10.06.2023, 02:52
 */
interface ChangeTrackingStatus {
    fun changeTrackingStatus(id: Long, tracking: Boolean)
}