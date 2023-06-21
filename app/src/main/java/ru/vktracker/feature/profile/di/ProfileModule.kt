package ru.vktracker.feature.profile.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.common.text.UsernameFormat
import ru.vktracker.core.ui.Communication
import ru.vktracker.core.ui.dialog.AlertDialogCommunication
import ru.vktracker.core.ui.navigation.Screen
import ru.vktracker.core.ui.navigation.ScreenCommunication
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.data.core.cache.Serialization
import ru.vktracker.data.core.cache.UserCacheToCommonMapper
import ru.vktracker.data.core.cache.UserToCacheMapper
import ru.vktracker.data.profile.BaseProfileRepository
import ru.vktracker.data.profile.cache.ProfileCacheDataSource
import ru.vktracker.data.profile.cache.ProfilePreferencesDataStore
import ru.vktracker.data.profile.cloud.ProfileCloudDataSource
import ru.vktracker.data.profile.cloud.ProfileVkApiService
import ru.vktracker.feature.profile.domain.ProfileInteractor
import ru.vktracker.feature.profile.ui.ProfileHandleDomainRequest
import ru.vktracker.feature.profile.ui.ProfileUiCommunication
import ru.vktracker.feature.profile.ui.UserToProfileUiMapper
import ru.vktracker.feature.profile.ui.nabigation.ProfileNavigation
import javax.inject.Qualifier

/**
 * @author Danil Glazkov on 17.06.2023, 07:34
 */
@Module
@InstallIn(ViewModelComponent::class)
class ProfileModule {

    @Qualifier
    annotation class ModuleQualifier

    @Module
    @InstallIn(ViewModelComponent::class)
    interface Bind {

        @Binds
        @ModuleQualifier
        fun observerCommunication(
            @ModuleQualifier communication: ScreenCommunication
        ) : Communication.Observe<Screen>
    }


    private val communication = ProfileUiCommunication.Base()
    private val screenCommunication = ScreenCommunication.Base()
    private val userToProfileUiMapper = UserToProfileUiMapper.Base()

    @Provides
    fun provideCommunication(): ProfileUiCommunication = communication

    @Provides
    @ModuleQualifier
    fun provideScreenCommunication(): ScreenCommunication = screenCommunication

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
                    ProfilePreferencesDataStore(resources.preferences(""), serialization),
                    UserCacheToCommonMapper(),
                    UserToCacheMapper(),
                ),
            )
        )
    }

    @Provides
    fun provideMapperToProfile(): UserToProfileUiMapper = userToProfileUiMapper

    @Provides
    fun provideProfileNavigation(): ProfileNavigation = ProfileNavigation.Base(screenCommunication)

    @Provides
    fun provideProfileHandleDomainRequest(
        dispatchers: CoroutineDispatchers,
    ) : ProfileHandleDomainRequest =
        ProfileHandleDomainRequest.Base(userToProfileUiMapper, communication, dispatchers)

    @Provides
    @ModuleQualifier
    fun provideAlertDialogCommunication(): AlertDialogCommunication =
        AlertDialogCommunication.Base()
}