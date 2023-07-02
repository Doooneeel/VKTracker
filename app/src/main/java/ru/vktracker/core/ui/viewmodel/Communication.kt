package ru.vktracker.core.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle

/**
 * @author Danil Glazkov on 01.06.2023, 22:05
 */
interface Communication {

    interface Update<T> {
        fun put(value: T)
    }

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Mutable<T> : Observe<T>, Update<T>


    abstract class Abstract<T>(protected val liveData: MutableLiveData<T>) : Mutable<T> {
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            liveData.observe(owner, observer)
    }

    abstract class Ui<T>(
        liveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(liveData) {
        override fun put(value: T) { liveData.value = value!! }
    }

    abstract class Post<T>(
        liveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(liveData) {
        override fun put(value: T) = liveData.postValue(value!!)
    }

    abstract class SingleUi<T> : Ui<T>(SingleLiveEvent())

    abstract class SinglePost<T> : Post<T>(SingleLiveEvent())

    abstract class SavedStateUi<T>(
        savedState: SavedStateHandle,
        key: String
    ) : Ui<T>(savedState.getLiveData(key))

    abstract class SavedStatePost<T>(
        savedState: SavedStateHandle,
        key: String
    ) : Post<T>(savedState.getLiveData(key))

}