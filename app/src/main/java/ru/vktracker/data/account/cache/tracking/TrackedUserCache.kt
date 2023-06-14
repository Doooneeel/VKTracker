package ru.vktracker.data.account.cache.tracking

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Danil Glazkov on 04.06.2023, 21:54
 */
@Entity(tableName = "tracked_users")
data class TrackedUserCache(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "tracking_start_time")
    val trackingStartTime: Long
)