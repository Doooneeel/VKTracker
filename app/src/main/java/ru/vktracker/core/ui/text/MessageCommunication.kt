package ru.vktracker.core.ui.text

import androidx.lifecycle.SavedStateHandle
import ru.vktracker.core.ui.viewmodel.Communication

/**
 * @author Danil Glazkov on 26.06.2023, 18:54
 */
interface MessageCommunication : Communication.Mutable<Message> {

    class Base : Communication.Ui<Message>(), MessageCommunication

    class SavedState(savedState: SavedStateHandle) : Communication.SavedStateUi<Message>(
        savedState, "MessageSavedStateCommunication"
    )

}