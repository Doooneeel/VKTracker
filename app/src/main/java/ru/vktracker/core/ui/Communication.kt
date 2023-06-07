package ru.vktracker.core.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * @author Danil Glazkov on 01.06.2023, 22:05
 */
interface Communication {

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Update<T> {
        fun put(value: T)
    }

    interface Mutable<T> : Observe<T>, Update<T>


    abstract class Abstract<T>(protected val liveData: MutableLiveData<T>) : Mutable<T> {
        protected val value get() = checkNotNull(liveData.value)

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }

    abstract class Ui<T : Any>(
        liveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(liveData) {
        constructor(initValue: T) : this(MutableLiveData(initValue))

        override fun put(value: T) { liveData.value = value }
    }

    abstract class Post<T : Any>(
        liveData: MutableLiveData<T> = MutableLiveData(),
    ) : Abstract<T>(liveData) {
        override fun put(value: T) = liveData.postValue(value)
    }

}