package ru.vktracker.feature.profile.ui

import kotlinx.coroutines.CoroutineScope
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.common.user.User
import ru.vktracker.core.ui.Communication

/**
 * @author Danil Glazkov on 17.06.2023, 11:48
 */
interface ProfileHandleDomainRequest {

    fun handleBlock(scope: CoroutineScope, block: suspend ((User) -> Unit) -> Unit)


    class Base(
        private val mapperToUi: UserToProfileUiMapper,
        private val communication: Communication.Update<ProfileUi>,
        private val dispatchers: CoroutineDispatchers
    ) : ProfileHandleDomainRequest {

        override fun handleBlock(scope: CoroutineScope, block: suspend ((User) -> Unit) -> Unit) {
            dispatchers.io(scope) {
                block.invoke { resultProfile: User ->
                    val profileUi: ProfileUi = resultProfile.map(mapperToUi)

                    dispatchers.ui(this) {
                        communication.put(profileUi)
                    }
                }
            }
        }
    }
}