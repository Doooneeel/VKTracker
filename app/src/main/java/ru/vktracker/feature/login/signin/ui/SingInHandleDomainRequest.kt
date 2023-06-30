package ru.vktracker.feature.login.signin.ui

import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.common.HandleDomainRequest
import ru.vktracker.core.common.HandleDomainRequest.*
import ru.vktracker.feature.login.signin.domain.SignInDomainResponse

/**
 * @author Danil Glazkov on 24.06.2023, 14:22
 */
interface SingInHandleDomainRequest : HandleDomainRequest<SignInDomainResponse> {

    class Base(
        private val mapper: SignInResponseMapper,
        dispatchers: CoroutineDispatchers
    ) : Abstract<SignInDomainResponse>(dispatchers), SingInHandleDomainRequest {
        override fun launchUi(response: SignInDomainResponse) =
            response.map(mapper)
    }
}