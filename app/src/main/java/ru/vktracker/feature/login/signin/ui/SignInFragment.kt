package ru.vktracker.feature.login.signin.ui

import android.app.Activity
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import com.vk.api.sdk.VK
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.BaseFragmentViewModel
import ru.vktracker.core.ui.Throttle
import ru.vktracker.core.ui.OnThrottleClickListener
import ru.vktracker.core.ui.navigation.Screen
import ru.vktracker.databinding.FragmentSignInBinding
import ru.vktracker.databinding.FragmentSignInBinding as Binding

/**
 * @author Danil Glazkov on 01.06.2023, 22:28
 */
@AndroidEntryPoint
class SignInFragment : BaseFragmentViewModel<Binding, SignInViewModel>(ID, Binding::inflate) {

    override val viewModel: SignInViewModel by viewModels<SignInViewModel.Base>()
    override val hasBottomBar = true

    override fun FragmentSignInBinding.registerClickListeners(throttle: Throttle) {
        toolBar.setNavigationOnClickListener(
            OnThrottleClickListener.Longer(throttle) { viewModel.navigateToWelcomeScreen() }
        )
        loginFloatingActionButton.setOnClickListener(
            OnThrottleClickListener.Longer(throttle) { viewModel.navigateToTabsScreen() }
        )
    }

    override fun SignInViewModel.registerObservers() {
        observeScreen(viewLifecycleOwner) { screen: Screen ->
            screen.navigate(navController)
        }
    }

    companion object {
        private val ID = R.layout.fragment_sign_in
    }

}