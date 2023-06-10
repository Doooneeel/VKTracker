package ru.vktracker.feature.account.users.tabs

import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.HandleDomainRequest
import ru.vktracker.feature.account.users.domain.AccountUsersDomainResponse

/**
 * @author Danil Glazkov on 10.06.2023, 08:10
 */
interface AccountUsersHandleDomainRequest : HandleDomainRequest<AccountUsersDomainResponse> {

    class Base(
        private val responseMapper: AccountUsersDomainResponseMapper,
        dispatchers: CoroutineDispatchers,
    ) : HandleDomainRequest.Abstract<AccountUsersDomainResponse>(dispatchers),
        AccountUsersHandleDomainRequest
    {
        override fun launchUi(response: AccountUsersDomainResponse) = response.map(responseMapper)
    }
}