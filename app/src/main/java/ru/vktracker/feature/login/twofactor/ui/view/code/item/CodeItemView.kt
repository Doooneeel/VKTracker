package ru.vktracker.feature.login.twofactor.ui.view.code.item

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Size
import android.view.View
import ru.vktracker.feature.login.twofactor.ui.view.code.style.CodeItemDeclaredStyle
import ru.vktracker.feature.login.twofactor.ui.view.code.style.ProvideCodeItemDeclaredStyle

/**
 * @author Danil Glazkov on 05.07.2023, 13:12
 */
class CodeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context), CodeItemActions {

    private val declaredStyle: CodeItemDeclaredStyle = when (attrs) {
        null -> ProvideCodeItemDeclaredStyle.Default.style(context)
        else -> ProvideCodeItemDeclaredStyle.Base(attrs).style(context)
    }

    private val cursor = declaredStyle.cursorSymbol

    private var currentState: CodeItemState = CodeItemState.Empty

    private val backgroundRect = RectF()

    private val backgroundPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = declaredStyle.backgroundColor
        isAntiAlias = true
    }

    private val borderPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = declaredStyle.borderWidth
        color = declaredStyle.borderColor
        isAntiAlias = true
    }

    private val textPaint: Paint = Paint().apply {
        color = declaredStyle.textColor
        textSize = declaredStyle.textSize.toFloat()
        typeface = declaredStyle.typeface
        textAlign = Paint.Align.CENTER
        isAntiAlias = true
    }

    private val textSize: Size = calculateTextSize()

    override fun update(state: CodeItemState) {
        if (currentState == state) return
        currentState = state

        val animator: ObjectAnimator? = state.borderAnimator(borderPaint, declaredStyle)

        if (animator != null) {
            animator.duration = declaredStyle.borderAnimationDuration.toLong()
            animator.addUpdateListener { invalidate() }
            animator.start()
        } else {
            invalidate()
        }
    }

    override fun symbol() = currentState.symbol(cursor)

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val halfWidth: Float = borderPaint.strokeWidth / 2

        backgroundRect.left = halfWidth
        backgroundRect.top = halfWidth
        backgroundRect.right = measuredWidth.toFloat() - halfWidth
        backgroundRect.bottom = measuredHeight.toFloat() - halfWidth
    }

    override fun onDraw(canvas: Canvas) {
        val stokeWidth: Float = borderPaint.strokeWidth / 2
        val radius: Float = declaredStyle.borderCornerRadius

        val x: Float = (backgroundRect.width() / 2) + stokeWidth
        val y: Float = (backgroundRect.height() / 2) + (textSize.height / 2) + stokeWidth

        canvas.drawRoundRect(backgroundRect, radius, radius, backgroundPaint)
        canvas.drawRoundRect(backgroundRect, radius, radius, borderPaint)
        canvas.drawText(currentState.symbol(cursor).toString(), x, y, textPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) = setMeasuredDimension(
        resolveSizeAndState(declaredStyle.width, widthMeasureSpec, 0),
        resolveSizeAndState(declaredStyle.height, heightMeasureSpec, 0)
    )

    private fun calculateTextSize(): Size {
        val textBounds = Rect()
        textPaint.getTextBounds("0", 0, 1, textBounds)

        return Size(textBounds.width(), textBounds.height())
    }
}