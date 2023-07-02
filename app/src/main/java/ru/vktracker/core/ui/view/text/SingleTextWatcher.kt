package ru.vktracker.core.ui.view.text

import android.text.Editable
import android.text.TextWatcher
import android.view.View

/**
 * @author Danil Glazkov on 24.06.2023, 14:45
 */
interface SingleTextWatcher : TextWatcher {

    fun onTextChanged(s: CharSequence)

    abstract class Abstract : SingleTextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence) = Unit

        final override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
            onTextChanged(s ?: "")
    }

    class OnTextChanged(private val onChange: (CharSequence) -> Unit) : Abstract() {
        override fun onTextChanged(s: CharSequence) = onChange.invoke(s)
    }

    class FocusedOnTextChanged(
        private val mustHaveFocus: View,
        private val onChange: (CharSequence) -> Unit
    ) : Abstract() {
        override fun onTextChanged(s: CharSequence) {
            if (mustHaveFocus.hasFocus()) onChange.invoke(s)
        }
    }

}