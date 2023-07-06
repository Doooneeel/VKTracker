package ru.vktracker.feature.login.twofactor.ui.view.code

import android.content.Context
import android.os.Parcelable
import android.text.InputType
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import kotlinx.parcelize.Parcelize
import ru.vktracker.core.ui.ManageKeyboard
import ru.vktracker.feature.login.twofactor.ui.view.code.listeners.CodeOnChangeListener
import ru.vktracker.feature.login.twofactor.ui.view.code.listeners.SetCodeOnChangeListener
import ru.vktracker.feature.login.twofactor.ui.view.code.style.CodeViewDeclaredStyle
import ru.vktracker.feature.login.twofactor.ui.view.code.style.ProvideCodeDeclaredStyle
import ru.vktracker.core.ui.view.common.AbstractView

/**
 * @author Danil Glazkov on 05.07.2023, 13:25
 */
class CodeConfirmationView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayoutCompat(context, attrs, defStyleAttr),
    SetCodeOnChangeListener,
    AbstractView.Code
{
    private var code: String = ""
    private val codeLength: Int
    private val viewStyle: CodeViewDeclaredStyle
    private val itemSubviews get() = children.filterIsInstance<CodeItemView>()
    private val manageKeyboard = ManageKeyboard.Base
    private var onChangeListener: CodeOnChangeListener = CodeOnChangeListener.Unit

    init {
        isFocusable = true
        isFocusableInTouchMode = true

        viewStyle = when (attrs) {
            null -> ProvideCodeDeclaredStyle.Default.style(context)
            else -> ProvideCodeDeclaredStyle.Base(attrs).style(context)
        }
        codeLength = viewStyle.codeLength

        removeAllViews()
        updateState()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        setOnKeyListener { _, keyCode, event ->
            if (event.action != KeyEvent.ACTION_DOWN) return@setOnKeyListener false

            when (event.keyCode) {
                in KeyEvent.KEYCODE_0..KeyEvent.KEYCODE_9 -> appendSymbol(
                    event.keyCharacterMap.getNumber(keyCode)
                )
                KeyEvent.KEYCODE_DEL -> removeLastSymbol()
                KeyEvent.KEYCODE_ENTER -> manageKeyboard.hideKeyboard(this)
                else -> return@setOnKeyListener false
            }
            true
        }
        setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) manageKeyboard.showKeyboard(view)
            else manageKeyboard.hideKeyboard(view)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && requestFocus()) {
            performClick()
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        manageKeyboard.showKeyboard(this)
        return super.performClick()
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection? {
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER
        outAttrs.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI

        return super.onCreateInputConnection(outAttrs)
    }

    override fun code(): String = code

    override fun onCheckIsTextEditor(): Boolean = true

    override fun setOnCodeChangeListener(listener: CodeOnChangeListener) {
        onChangeListener = listener
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState: Parcelable = super.onSaveInstanceState() ?: return null
        return SavedState(superState, code)
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            changeCode(state.enteredCode)
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    @Parcelize
    private class SavedState(
        val parcelable: Parcelable,
        val enteredCode: String,
    ) : BaseSavedState(parcelable)

    private fun updateState() {
        if (codeLength != itemSubviews.count()) {
            setupSubviews()
        }
        val viewCode: String = itemSubviews.map { it.code() }
            .filterNot { it == " " }
            .joinToString("")

        if (viewCode != code) {
            itemSubviews.forEachIndexed { index: Int, view: CodeItemView ->
                val symbol = code.getOrElse(index) { EMPTY }

                view.updateState(
                    when (code.length == codeLength) {
                        true -> CodeItemState.Complete(symbol)
                        else -> CodeItemState.Input(symbol, code.length == index)
                    }
                )
            }
        }
    }

    private fun changeCode(code: String) {
        onChangeListener.onCodeChange(code, code.length == codeLength)
        this.code = code
        updateState()
    }

    private fun setupSubviews() {
        removeAllViews()

        repeat(codeLength) { itemIndex: Int ->
            val codeItemView = CodeItemView(context, attrs)

            codeItemView.updateState(
                CodeItemState.Input(EMPTY, code.length == itemIndex)
            )
            addView(codeItemView)

            if (itemIndex < codeLength - 1) {
                val space = View(context)
                space.layoutParams = ViewGroup.LayoutParams(viewStyle.itemsSpacing, 0)

                addView(space)
            }
        }
    }

    private fun removeLastSymbol() = changeCode(code.dropLast(1))

    private fun appendSymbol(symbol: Char) {
        if (code.length < codeLength) changeCode(code + symbol)
    }

    companion object {
        private const val EMPTY = ' '
    }

}