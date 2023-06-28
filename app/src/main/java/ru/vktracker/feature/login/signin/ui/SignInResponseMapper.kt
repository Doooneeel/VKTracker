package ru.vktracker.feature.login.signin.ui

import ru.vktracker.feature.login.signin.domain.SignInDomainResponse
import java.lang.Exception

/**
 * @author Danil Glazkov on 25.06.2023, 14:02
 */
interface SignInResponseMapper : SignInDomainResponse.Mapper<Unit> {

    class Base(
        private val navigation: SignInNavigation.Internal,
        private val handleError: SignInHandleError
    ) : SignInResponseMapper {

        override fun success(token: CharArray) = navigation.navigateToTabsScreen()

        override fun failure(exception: Exception) = handleError.handle(exception)

        override fun mapTwoFactorAuth(phoneMask: String, redirectUrl: String) =
            navigation.navigateToTwoFactorScreen(phoneMask, redirectUrl)

    }
}