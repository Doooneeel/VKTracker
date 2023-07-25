package ru.vktracker.feature.login.signin.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.vktracker.R
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.core.ui.text.validate.*
import ru.vktracker.feature.login.signin.domain.SighInInteractor
import ru.vktracker.feature.login.signin.ui.*
import ru.vktracker.feature.login.signin.ui.state.SignInHandleUiState
import ru.vktracker.feature.login.signin.ui.state.SignInUiStateCommunication
import ru.vktracker.feature.login.signin.ui.validate.SignInValidateInput

/**
 * @author Danil Glazkov on 22.06.2023, 19:05
 */
@Module
@InstallIn(ViewModelComponent::class)
class SingInModule {

    private val childNavigationCommunication = NavigationCommunication.Ui()

    @Provides
    @ViewModelScoped
    fun provideSignInUiStateCommunication(
        savedState: SavedStateHandle,
        resources: ManageResources
    ) : SignInUiStateCommunication {
        return SignInUiStateCommunication.SavedState(
            SignInHandleUiState.Base(resources),
            savedState
        )
    }


    @Provides
    @ViewModelScoped
    fun provideSignInNavigation(): SignInNavigation.Combined =
        SignInNavigation.Base(childNavigationCommunication)


    @Provides
    fun providesCommunications(
        signInUiStateCommunication: SignInUiStateCommunication
    ) : SignInCommunications.Mutable {
        return SignInCommunications.Base(signInUiStateCommunication, childNavigationCommunication)
    }


    @Provides
    fun provideSighInInteractor(): SighInInteractor = SighInInteractor.Base()


    @Provides
    fun provideSignInHandleError(resources: ManageResources): SignInHandleError =
        SignInHandleError(resources)


    @Provides
    fun provideValidateInput(resources: ManageResources): SignInValidateInput {
        return SignInValidateInput.Base(
            ValidateChain.AllValid(
                ValidateText.MinLength(
                    resources.number(R.integer.vk_login_min_length)
                ),
                ValidateChain.AnyValid(
                    ValidateEmail.AndroidPattern(),
                    ValidateNumberPhone.AndroidPattern(),
                )
            ),
            ValidateChain.AllValid(
                ValidatePassword.NotEmpty(),
                ValidatePassword.MinLength(
                    resources.number(R.integer.vk_password_min_length)
                )
            )
        )
    }
}