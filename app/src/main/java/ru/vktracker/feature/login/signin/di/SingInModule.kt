package ru.vktracker.feature.login.signin.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.vktracker.R
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.view.progress.ProgressCommunication
import ru.vktracker.core.ui.dialog.DialogCommunication
import ru.vktracker.core.ui.navigation.NavigationCommunication
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.core.ui.text.validate.*
import ru.vktracker.core.ui.view.fab.FabViewStateCommunication
import ru.vktracker.core.ui.view.state.ViewStateCommunication
import ru.vktracker.feature.login.signin.domain.SighInInteractor
import ru.vktracker.feature.login.signin.ui.*
import ru.vktracker.feature.login.signin.ui.validate.SignInValidateInput

/**
 * @author Danil Glazkov on 22.06.2023, 19:05
 */
@Module
@InstallIn(ViewModelComponent::class)
class SingInModule {

    private val childNavigationCommunication = NavigationCommunication.Base()
    private val progressCommunication = ProgressCommunication.Base()
    private val interfaceViewStateCommunication = ViewStateCommunication.Base()
    private val dialogCommunication = DialogCommunication.Base()

    @Provides
    @ViewModelScoped
    fun provideFabViewStateCommunication(savedState: SavedStateHandle): FabViewStateCommunication =
        FabViewStateCommunication.SavedState(savedState)

    @Provides
    @ViewModelScoped
    fun provideSignInNavigation(): SignInNavigation.Combine =
        SignInNavigation.Base(childNavigationCommunication)

    @Provides
    fun providesCommunications(
        fabViewStateCommunication: FabViewStateCommunication
    ) : SignInCommunications.Mutable {
        return SignInCommunications.Base(
            interfaceViewStateCommunication,
            fabViewStateCommunication,
            dialogCommunication,
            progressCommunication,
            childNavigationCommunication,
        )
    }

    @Provides
    fun provideSighInInteractor(): SighInInteractor = SighInInteractor.Base()

    @Provides
    fun provideSingInHandleDomainRequest(
        dispatchers: CoroutineDispatchers,
        resources: ManageResources,
        navigation: SignInNavigation.Combine,
        fabViewStateCommunication: FabViewStateCommunication
    ) : SingInHandleDomainRequest {
        return SingInHandleDomainRequest.Base(
            SignInResponseMapper.Base(
                dialogCommunication,
                fabViewStateCommunication,
                interfaceViewStateCommunication,
                progressCommunication,
                navigation,
                SignInHandleError(resources)
            ),
            dispatchers
        )
    }

    @Provides
    fun provideValidateInput(resources: ManageResources): SignInValidateInput {
        return SignInValidateInput.Base(
            ValidateChain.AnyValid(
                ValidateEmail(),
                ValidateNumberPhone()
            ),
            ValidatePassword.MinLength(
                resources.number(R.integer.vk_password_min_length)
            )
        )
    }
}