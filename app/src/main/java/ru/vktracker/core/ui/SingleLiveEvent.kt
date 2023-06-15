package ru.vktracker.core.ui

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Danil Glazkov on 15.06.2023, 22:32
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val state = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) = super.observe(owner) {
        if (state.compareAndSet(true, false)) {
            observer.onChanged(it)
        }
    }

    @MainThread
    override fun setValue(value: T) {
        state.set(true)
        super.setValue(value)
    }
}