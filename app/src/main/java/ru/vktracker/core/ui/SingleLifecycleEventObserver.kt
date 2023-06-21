package ru.vktracker.core.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author Danil Glazkov on 20.06.2023, 12:50
 */
interface SingleLifecycleEventObserver : LifecycleEventObserver {

    abstract class Abstract(
        private val event: Lifecycle.Event,
        private val block: () -> Unit
    ) : SingleLifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (this.event == event) block.invoke()
        }
    }

    class Pause (block: () -> Unit) : Abstract(Lifecycle.Event.ON_PAUSE, block)

    class Create (block: () -> Unit) : Abstract(Lifecycle.Event.ON_CREATE, block)

    class Destroy (block: () -> Unit) : Abstract(Lifecycle.Event.ON_DESTROY, block)

}