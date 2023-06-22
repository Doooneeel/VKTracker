package ru.vktracker.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import ru.vktracker.core.common.CoroutineDispatchers

/**
 * @author Danil Glazkov on 01.06.2023, 03:58
 */
interface BaseViewModel {

    object Unit : BaseViewModel


    abstract class Abstract(
        private val dispatchers: CoroutineDispatchers
    ) : ViewModel(), BaseViewModel {

        protected fun <T> handle(block: suspend () -> T): Job {
            return dispatchers.io(viewModelScope) {
                block.invoke()
            }
        }
    }
}