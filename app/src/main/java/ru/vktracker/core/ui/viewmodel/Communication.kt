package ru.vktracker.core.ui.viewmodel

import android.os.Parcelable
import androidx.lifecycle.*
import ru.vktracker.core.ui.state.HandleUiState

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

    abstract class SavedStateUi<T : Parcelable>(savedState: SavedStateHandle, key: String) :
        Ui<T>(savedState.getLiveData(key))

    abstract class SavedStateUiWithHandler<T : Parcelable>(
        handleUiState: HandleUiState<T>,
        savedState: SavedStateHandle,
        key: String
    ) : SavedStateUi<T>(savedState, key) {
        init {
            val newState: T? = handleUiState.handle(liveData.value)
            if (newState != null) {
                this.put(newState)
            }
        }
    }

}