package ru.vktracker.main.di

import androidx.room.Room
import com.vk.api.sdk.VK
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vktracker.data.account.cache.tracking.ProvideTrackedUsersDao
import ru.vktracker.data.core.MainDatabase
import javax.inject.Singleton

/**
 * @author Danil Glazkov on 10.06.2023, 09:45
 */
@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Bind {

        @Binds
        @Singleton
        fun bindTrackedUsersDao(database: MainDatabase): ProvideTrackedUsersDao

    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase {
        val id = VK.getUserId().toString()

        return Room.databaseBuilder(context, MainDatabase::class.java, id)
            .allowMainThreadQueries()
            .build()
    }

}