package ru.vktracker.core.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * @author Danil Glazkov on 11.06.2023, 00:33
 */
interface ProgressCommunication : Communication.Mutable<Visibility> {

    interface Observe {
        fun observeProgress(owner: LifecycleOwner, observer: Observer<Visibility>)
    }

    class Base : Communication.Ui<Visibility>(), ProgressCommunication
}