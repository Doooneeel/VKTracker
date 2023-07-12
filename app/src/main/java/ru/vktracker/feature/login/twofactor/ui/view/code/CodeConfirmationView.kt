package ru.vktracker.feature.login.twofactor.ui.view.code

import android.content.Context
import android.os.Parcelable
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
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
import ru.vktracker.feature.login.twofactor.ui.view.code.style.CodeViewDeclaredStyle
import ru.vktracker.feature.login.twofactor.ui.view.code.style.ProvideCodeDeclaredStyle
import ru.vktracker.feature.login.twofactor.ui.view.code.item.CodeItemState
import ru.vktracker.feature.login.twofactor.ui.view.code.item.CodeItemView

/**
 * @author Danil Glazkov on 05.07.2023, 13:25
 */
class CodeConfirmationView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayoutCompat(context, attrs, defStyleAttr), CodeConfirmationViewActions {

    private var code: String = ""

    private val declaredStyle: CodeViewDeclaredStyle = when (attrs) {
        null -> ProvideCodeDeclaredStyle.Default.style(context)
        else -> ProvideCodeDeclaredStyle.Base(attrs).style(context)
    }

    private val codeLength: Int = declaredStyle.codeLength
    private val itemSubviews get() = children.filterIsInstance<CodeItemView>()

    private val manageKeyboard = ManageKeyboard.Base
    private var onChangeListener: CodeOnChangeListener = CodeOnChangeListener.Unit

    init {
        isFocusable = true
        isFocusableInTouchMode = true
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
        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) manageKeyboard.showKeyboard(this) else manageKeyboard.hideKeyboard(this)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && requestFocus()) {
            manageKeyboard.showKeyboard(this)
            performClick()
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        super.performClick()
        requestFocus()
        updateState()
        return true
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection? {
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER
        outAttrs.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI

        return super.onCreateInputConnection(outAttrs)
    }

    override fun isComplete(): Boolean = code.length == codeLength

    override fun clear() = changeCode("")

    override fun code(): String = code

    override fun onCheckIsTextEditor(): Boolean = true

    override fun setOnCodeChangeListener(listener: CodeOnChangeListener) {
        onChangeListener = listener
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState: Parcelable = super.onSaveInstanceState() ?: return null
        return SavedState(superState, code, hasFocus())
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            Log.d("TTTT", state.hasFocus.toString())
            if (state.hasFocus) {
                requestFocus()
                updateKeyboard(true)
            }
            changeCode(state.enteredCode)

        } else {
            super.onRestoreInstanceState(state)
        }
    }

    @Parcelize
    private class SavedState(
        val parcelable: Parcelable,
        val enteredCode: String,
        val hasFocus: Boolean
    ) : BaseSavedState(parcelable)


    private fun updateState() {
        if (codeLength != itemSubviews.count()) {
            setupSubviews()
        }

        val viewCode: String = itemSubviews.map { it.symbol() }
            .filterNot { it == EMPTY }
            .joinToString("")

        if (viewCode != code) {
            itemSubviews.forEachIndexed { index: Int, view: CodeItemView ->
                val symbol: Char = code.getOrNull(index) ?: EMPTY
                val isLastItem: Boolean = index == codeLength - 1

                view.update(
                    if (isComplete())
                        completeState(symbol, isLastItem)
                    else
                        inputState(symbol, code.length == index, isLastItem)
                )
            }
        }
    }


    private fun inputState(symbol: Char, isActive: Boolean, isLastItem: Boolean) = if (isLastItem)
        CodeItemState.InputLastItem(symbol, isActive)
    else
        CodeItemState.Input(symbol, isActive, hasFocus())


    private fun completeState(symbol: Char, isLastItem: Boolean) = if (isLastItem)
        CodeItemState.CompleteLastItem(symbol)
    else
        CodeItemState.Complete(symbol)


    private fun changeCode(code: String) {
        this.code = code
        onChangeListener.onCodeChange(code, isComplete())
        updateState()
    }

    private fun setupSubviews() {
        removeAllViews()

        repeat(codeLength) { itemIndex: Int ->
            val codeItemView = CodeItemView(context, attrs)

            codeItemView.update(CodeItemState.Input(EMPTY, code.length == itemIndex, hasFocus()))
            addView(codeItemView)

            if (itemIndex < codeLength - 1) {
                val space = View(context)
                space.layoutParams = ViewGroup.LayoutParams(declaredStyle.itemsSpacing, 0)

                addView(space)
            }
        }
    }

    private fun updateKeyboard(show: Boolean) {
        if (show) manageKeyboard.showKeyboard(this)
        else manageKeyboard.hideKeyboard(this)
    }

    private fun removeLastSymbol() = changeCode(code.dropLast(1))

    private fun appendSymbol(symbol: Char) {
        if (code.length < codeLength) changeCode(code + symbol)
    }

    companion object {
        private const val EMPTY = ' '
    }

}