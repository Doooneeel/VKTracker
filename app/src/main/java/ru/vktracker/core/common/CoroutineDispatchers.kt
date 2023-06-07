package ru.vktracker.core.common

import kotlinx.coroutines.*

/**
 * @author Danil Glazkov on 01.06.2023, 21:55
 */
interface CoroutineDispatchers {

    suspend fun changeToUi(block: suspend CoroutineScope. () -> Unit)

     fun ui(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job

     fun io(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job


    abstract class Abstract(
        private val ui: CoroutineDispatcher,
        private val background: CoroutineDispatcher,
    ) : CoroutineDispatchers {

        override fun io(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) : Job = scope.launch(background, block = block)

        override fun ui(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) : Job = scope.launch(ui, block = block)

        override suspend fun changeToUi(block: suspend CoroutineScope.() -> Unit) =
            withContext(ui, block)
    }

    class Base : Abstract(Dispatchers.Main, Dispatchers.IO)
}