package ru.vktracker.feature.profile.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.vktracker.core.common.Logout
import ru.vktracker.core.common.text.UsernameFormat
import ru.vktracker.core.ui.dialog.DialogCommunication
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.data.core.cache.Serialization
import ru.vktracker.data.core.cache.UserCacheToCommonMapper
import ru.vktracker.data.core.cache.UserToCacheMapper
import ru.vktracker.data.profile.BaseProfileRepository
import ru.vktracker.data.profile.cache.*
import ru.vktracker.data.profile.cloud.ProfileCloudDataSource
import ru.vktracker.data.profile.cloud.ProfileVkApiService
import ru.vktracker.feature.profile.domain.ProfileInteractor
import ru.vktracker.feature.profile.ui.*
import ru.vktracker.feature.profile.ui.navigation.ProfileNavigation
import ru.vktracker.feature.profile.ui.state.ProfileUiStateCommunication

/**
 * @author Danil Glazkov on 17.06.2023, 07:34
 */
@Module
@InstallIn(ViewModelComponent::class)
class ProfileModule {

    private val navigationCommunication = NavigationCommunication.Ui()
    private val mainNavigationCommunication = NavigationCommunication.Ui()

    @Provides
    fun provideCommunications(
        profileUiStateCommunication: ProfileUiStateCommunication
    ) : ProfileCommunications.Combined = ProfileCommunications.Base(
        profileUiStateCommunication,
        DialogCommunication.Base(),
        navigationCommunication,
        mainNavigationCommunication,
    )

    @Provides
    fun provideLogout(): Logout = VkLogout()

    @Provides
    fun provideUiStateCommunication(savedState: SavedStateHandle): ProfileUiStateCommunication =
        ProfileUiStateCommunication.SavedState(savedState)

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
    fun provideUserToProfileUiMapper(): UserToProfileUiMapper = UserToProfileUiMapper.Base()

    @Provides
    @ViewModelScoped
    fun provideProfileNavigation(): ProfileNavigation.Combined = ProfileNavigation.Base(
        navigationCommunication,
        mainNavigationCommunication
    )
}