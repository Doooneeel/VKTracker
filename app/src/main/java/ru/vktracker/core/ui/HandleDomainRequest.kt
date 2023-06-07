package ru.vktracker.core.ui

import kotlinx.coroutines.CoroutineScope
import ru.vktracker.core.common.CoroutineDispatchers

/**
 * @author Danil Glazkov on 07.06.2023, 01:19
 */
interface HandleDomainRequest<T> {

    fun handle(scope: CoroutineScope, block: suspend () -> T)


    abstract class AbstractWithMapper<D, U>(
        private val dispatchers: CoroutineDispatchers,
    ) : HandleDomainRequest<D> {

        protected abstract fun launchUi(result: U)

        protected abstract suspend fun map(response: D): U

        override fun handle(scope: CoroutineScope, block: suspend () -> D) {
            dispatchers.io(scope) {
                val response: D = block.invoke()
                val result: U = map(response)

                dispatchers.changeToUi {
                    launchUi(result)
                }
            }
        }
    }

    abstract class Abstract<T>(
        private val dispatchers: CoroutineDispatchers,
    ) : HandleDomainRequest<T> {
        protected abstract fun launchUi(response: T)

        override fun handle(scope: CoroutineScope, block: suspend () -> T) {
            dispatchers.io(scope) {
                val result: T = block.invoke()

                dispatchers.changeToUi {
                    launchUi(result)
                }
            }
        }
    }
}