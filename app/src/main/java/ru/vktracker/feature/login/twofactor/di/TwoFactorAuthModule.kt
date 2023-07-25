package ru.vktracker.feature.login.twofactor.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.feature.login.twofactor.domain.TwoFactorAuthInteractor
import ru.vktracker.feature.login.twofactor.ui.*
import ru.vktracker.feature.login.twofactor.ui.state.HandleTwoFactorUiState
import ru.vktracker.feature.login.twofactor.ui.state.TwoFactorUiStateCommunication

/**
 * @author Danil Glazkov on 25.06.2023, 18:16
 */
@Module
@InstallIn(ViewModelComponent::class)
class TwoFactorAuthModule {

    private val navigationCommunication = NavigationCommunication.Ui()
    private val navigation = TwoFactorNavigation.Base(navigationCommunication)

    @Provides
    fun provideNavigation(): TwoFactorNavigation.Combined = navigation

    @Provides
    @ViewModelScoped
    fun provideTwoFactorUiStateCommunication(
        savedState: SavedStateHandle,
        resources: ManageResources,
    ) : TwoFactorUiStateCommunication =
        TwoFactorUiStateCommunication.SavedState(
            HandleTwoFactorUiState.Base(resources),
            savedState
        )

    @Provides
    @ViewModelScoped
    fun provideCommunications(
        communication: TwoFactorUiStateCommunication,
    ) : TwoFactorCommunications.Mutable =
        TwoFactorCommunications.Base(
            communication,
            navigationCommunication,
        )

    @Provides
    fun provideTwoFactorAuthInteractor(): TwoFactorAuthInteractor {
        return TwoFactorAuthInteractor.Base()
    }

    @Provides
    fun provideHandleError(manageResources: ManageResources): TwoFactorHandleError =
        TwoFactorHandleError.Base(manageResources)

    @Provides
    fun provideArgs(savedStateHandle: SavedStateHandle): TwoFactorAuthArgs =
        TwoFactorAuthArgs.Base(savedStateHandle)
}