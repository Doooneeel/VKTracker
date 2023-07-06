package ru.vktracker.feature.login.twofactor.ui.view.code

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Size
import android.view.View
import ru.vktracker.feature.login.twofactor.ui.view.code.style.ProvideCodeItemDeclaredStyle
import ru.vktracker.core.ui.view.common.AbstractView

/**
 * @author Danil Glazkov on 05.07.2023, 13:12
 */
class CodeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context),
    AbstractView.Code
{
    private val itemStyle = when (attrs) {
        null -> ProvideCodeItemDeclaredStyle.Default.style(context)
        else -> ProvideCodeItemDeclaredStyle.Base(attrs).style(context)
    }

    private var state: CodeItemState = CodeItemState.Empty
    private val argbEvaluator = ArgbEvaluator()
    private val backgroundRect = RectF()

    private val backgroundPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = itemStyle.backgroundColor
        isAntiAlias = true
    }

    private val borderPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = itemStyle.borderWidth
        color = itemStyle.borderColor
        isAntiAlias = true
    }

    private val textPaint: Paint = Paint().apply {
        color = itemStyle.textColor
        textSize = itemStyle.textSize.toFloat()
        typeface = itemStyle.typeface
        textAlign = Paint.Align.CENTER
        isAntiAlias = true
    }

    private val textSize: Size = calculateTextSize()

    fun updateState(state: CodeItemState) {
        if (this.state.compare(state).not()) {
            this.state = state
            animateBorderColorChange(state.isActive())
        }
    }

    override fun code() = state.symbol().toString()

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val borderWidthHalf: Float = borderPaint.strokeWidth / 2

        backgroundRect.left = borderWidthHalf
        backgroundRect.top = borderWidthHalf
        backgroundRect.right = measuredWidth.toFloat() - borderWidthHalf
        backgroundRect.bottom = measuredHeight.toFloat() - borderWidthHalf
    }

    override fun onDraw(canvas: Canvas) {
        val stokeWidth: Float = borderPaint.strokeWidth / 2
        val radius: Float = itemStyle.borderCornerRadius

        val symbol: Char = if (state.cursor()) itemStyle.cursorSymbol else state.symbol()
        val x: Float = (backgroundRect.width() / 2) + stokeWidth
        val y: Float = (backgroundRect.height() / 2) + (textSize.height / 2) + stokeWidth

        canvas.drawRoundRect(backgroundRect, radius, radius, backgroundPaint)
        canvas.drawRoundRect(backgroundRect, radius, radius, borderPaint)
        canvas.drawText(symbol.toString(), x, y, textPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) = setMeasuredDimension(
        resolveSizeAndState(itemStyle.width, widthMeasureSpec, 0),
        resolveSizeAndState(itemStyle.height, heightMeasureSpec, 0)
    )

    private fun calculateTextSize(): Size {
        val textBounds = Rect()
        textPaint.getTextBounds("0", 0, 1, textBounds)
        return Size(textBounds.width(), textBounds.height())
    }

    private fun animateBorderColorChange(isActive: Boolean) {
        val borderColor = itemStyle.borderColor
        val borderColorActive = itemStyle.borderColorActive

        if (borderColor == borderColorActive) return

        val from: Int
        val to: Int

        if (isActive) {
            from = borderColor
            to = borderColorActive
        } else {
            from = borderColorActive
            to = borderColor
        }

        val animator = ObjectAnimator.ofObject(borderPaint, "color", argbEvaluator, from, to)
        animator.duration = itemStyle.borderAnimationDuration.toLong()
        animator.addUpdateListener { invalidate() }
        animator.start()
    }

}