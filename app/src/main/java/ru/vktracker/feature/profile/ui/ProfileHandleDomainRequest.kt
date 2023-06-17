package ru.vktracker.feature.profile.ui

import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.common.User
import ru.vktracker.core.ui.Communication
import ru.vktracker.core.ui.HandleDomainRequest

/**
 * @author Danil Glazkov on 17.06.2023, 11:48
 */
interface ProfileHandleDomainRequest : HandleDomainRequest<User> {

    class Base(
        private val mapperToUi: UserToProfileUiMapper,
        private val communication: Communication.Update<ProfileUi>,
        dispatchers: CoroutineDispatchers
    ) : HandleDomainRequest.AbstractWithMapper<User, ProfileUi>(dispatchers),
        ProfileHandleDomainRequest
    {
        override fun launchUi(result: ProfileUi) = communication.put(result)

        override suspend fun map(response: User): ProfileUi = response.map(mapperToUi)
    }
}