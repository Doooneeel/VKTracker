package ru.vktracker.feature.login.signin.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.vktracker.R
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.core.ui.text.validate.*
import ru.vktracker.feature.login.signin.domain.SighInInteractor
import ru.vktracker.feature.login.signin.ui.*
import ru.vktracker.feature.login.signin.ui.state.SignInUiStateCommunication
import ru.vktracker.feature.login.signin.ui.validate.SignInValidateInput

/**
 * @author Danil Glazkov on 22.06.2023, 19:05
 */
@Module
@InstallIn(ViewModelComponent::class)
class SingInModule {

    private val childNavigationCommunication = NavigationCommunication.Base()

    @Provides
    @ViewModelScoped
    fun provideSignInUiStateCommunication(savedState: SavedStateHandle): SignInUiStateCommunication {
        return SignInUiStateCommunication.SavedState(savedState)
    }

    @Provides
    @ViewModelScoped
    fun provideSignInNavigation(): SignInNavigation.Combine =
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
    fun provideSingInHandleDomainRequest(
        dispatchers: CoroutineDispatchers,
        resources: ManageResources,
        navigation: SignInNavigation.Combine,
        signInUiStateCommunication: SignInUiStateCommunication
    ) : SingInHandleDomainRequest {
        return SingInHandleDomainRequest.Base(
            SignInResponseMapper.Base(
                signInUiStateCommunication,
                navigation,
                SignInHandleError(resources)
            ),
            dispatchers
        )
    }

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