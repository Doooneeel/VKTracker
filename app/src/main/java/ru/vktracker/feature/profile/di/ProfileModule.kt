package ru.vktracker.feature.profile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.common.text.UsernameFormat
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.data.core.cache.Serialization
import ru.vktracker.data.profile.BaseProfileRepository
import ru.vktracker.data.profile.cache.ProfileCacheDataSource
import ru.vktracker.data.profile.cache.ProfilePreferencesDataStore
import ru.vktracker.data.profile.cache.UserToProfileCacheMapper
import ru.vktracker.data.profile.cloud.ProfileCloudDataSource
import ru.vktracker.data.profile.cloud.ProfileVkApiService
import ru.vktracker.feature.profile.domain.ProfileInteractor
import ru.vktracker.feature.profile.ui.ProfileHandleDomainRequest
import ru.vktracker.feature.profile.ui.ProfileUiCommunication
import ru.vktracker.feature.profile.ui.UserToProfileUiMapper

/**
 * @author Danil Glazkov on 17.06.2023, 07:34
 */
@Module
@InstallIn(ViewModelComponent::class)
class ProfileModule {

    private val communication = ProfileUiCommunication.Base()

    @Provides
    fun provideCommunication(): ProfileUiCommunication = communication

    @Provides
    fun provideInteractor(
        resources: ManageResources,
        serialization: Serialization,
        usernameFormat: UsernameFormat,
    ) : ProfileInteractor {
        return ProfileInteractor.Base(
            BaseProfileRepository(
                ProfileCloudDataSource.Base(
                    ProfileVkApiService.Base(),
                    usernameFormat
                ),
                ProfileCacheDataSource.Base(
                    ProfilePreferencesDataStore(resources.preferences(""), serialization)
                ),
                UserToProfileCacheMapper()
            )
        )
    }

    @Provides
    fun provideProfileHandleDomainRequest(
        dispatchers: CoroutineDispatchers,
    ) : ProfileHandleDomainRequest {
        return ProfileHandleDomainRequest.Base(
            UserToProfileUiMapper.Base(),
            communication,
            dispatchers
        )
    }
}