package ru.vktracker.feature.profile.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.vktracker.core.ui.Communication

/**
 * @author Danil Glazkov on 17.06.2023, 07:18
 */
interface ProfileUiCommunication : Communication.Mutable<ProfileUi> {

    interface Observe {
        fun observeProfileUi(owner: LifecycleOwner, observer: Observer<ProfileUi>)
    }

    class Base : Communication.Ui<ProfileUi>(), ProfileUiCommunication

}