package ru.vktracker.feature.login.twofactor.ui.view.code.style

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue.*
import com.google.android.material.R as material
import com.google.android.material.color.MaterialColors
import ru.vktracker.R.styleable.*
import ru.vktracker.core.ui.view.common.ProvideDeclaredStyle
import ru.vktracker.core.ui.view.common.ProvideDeclaredStyle.*

/**
 * @author Danil Glazkov on 06.07.2023, 17:12
 */
interface ProvideCodeItemDeclaredStyle : ProvideDeclaredStyle<CodeItemDeclaredStyle> {

    object Default : ProvideCodeItemDeclaredStyle {

        private const val DEFAULT_CURSOR = '|'
        private const val BORDER_WIDTH = 2F
        private const val BORDER_RADIUS = 4F
        private const val TEXT_SIZE = 22F
        private const val SIZE = 48F
        private const val BORDER_ANIMATION_DURATION = 0

        private var style: CodeItemDeclaredStyle? = null

        override fun style(context: Context): CodeItemDeclaredStyle {
            if (style != null) return style!!

            val matrix = context.resources.displayMetrics
            val primaryColor = MaterialColors.getColor(context, material.attr.colorPrimary, Color.BLACK)
            val size = applyDimension(COMPLEX_UNIT_PX, SIZE, matrix).toInt()

            style = CodeItemDeclaredStyle(
                width = size,
                height = size,
                backgroundColor = Color.TRANSPARENT,
                borderColor = primaryColor,
                borderColorActive = primaryColor,
                borderWidth = applyDimension(COMPLEX_UNIT_PX, BORDER_WIDTH, matrix),
                borderCornerRadius = BORDER_RADIUS,
                textColor = primaryColor,
                textSize = applyDimension(COMPLEX_UNIT_SP, TEXT_SIZE, matrix).toInt(),
                typeface = Typeface.DEFAULT,
                cursorSymbol = DEFAULT_CURSOR,
                enableCursor = true,
                borderAnimationDuration = BORDER_ANIMATION_DURATION
            )
            return style!!
        }
    }

    class Base(attributeSet: AttributeSet) : Abstract<CodeItemDeclaredStyle>(attributeSet),
        ProvideCodeItemDeclaredStyle
    {
        override val styleRes: IntArray = CodeConfirmationView

        override fun TypedArray.style(context: Context): CodeItemDeclaredStyle {
            val defaultSymbolStyle = Default.style(context)

            val itemWidth: Int = getDimensionPixelSize(CodeConfirmationView_codeItemWidth,
                defaultSymbolStyle.width
            )
            val itemHeight: Int = getDimensionPixelSize(CodeConfirmationView_codeItemHeight,
                defaultSymbolStyle.height
            )
            val itemBackgroundColor = getColor(CodeConfirmationView_codeItemBackgroundColor,
                defaultSymbolStyle.backgroundColor
            )
            val itemBorderColor = getColor(CodeConfirmationView_codeItemBorderColor,
                defaultSymbolStyle.borderColor
            )
            val itemBorderActiveColor = getColor(CodeConfirmationView_codeItemBorderActiveColor,
                defaultSymbolStyle.borderColorActive
            )
            val itemTextColor = getColor(CodeConfirmationView_codeItemTextColor,
                defaultSymbolStyle.textColor
            )
            val itemBorderWidth = getDimensionPixelSize(CodeConfirmationView_codeItemBorderWidth,
                defaultSymbolStyle.borderWidth.toInt()
            )
            val itemTextSize = getDimensionPixelSize(CodeConfirmationView_codeItemTextSize,
                defaultSymbolStyle.textSize
            )
            val cornerRadius = getDimension(CodeConfirmationView_codeItemBorderCornerRadius,
                defaultSymbolStyle.borderCornerRadius
            )
            val enableCursor = getBoolean(CodeConfirmationView_codeEnableCursor,
                defaultSymbolStyle.enableCursor
            )
            val borderAnimationDuration = getInteger(
                CodeConfirmationView_codeItemBorderAnimationDuration,
                defaultSymbolStyle.borderAnimationDuration
            )
            val cursorSymbol: Char = if (enableCursor) {
                val defaultCursorSymbol = defaultSymbolStyle.cursorSymbol
                val codeCursorSymbol = getString(CodeConfirmationView_codeCursorSymbol)
                (codeCursorSymbol ?: defaultCursorSymbol.toString()).getOrElse(0) {
                    defaultCursorSymbol
                }
            } else {
                EMPTY_CURSOR
            }
            val itemTextFont = getFont(CodeConfirmationView_codeItemTextFont) ?: Typeface.DEFAULT

            return CodeItemDeclaredStyle(
                width = itemWidth,
                height = itemHeight,
                backgroundColor = itemBackgroundColor,
                borderColor = itemBorderColor,
                borderColorActive = itemBorderActiveColor,
                borderWidth = itemBorderWidth.toFloat(),
                borderCornerRadius = cornerRadius,
                textColor = itemTextColor,
                textSize = itemTextSize,
                typeface = itemTextFont,
                cursorSymbol = cursorSymbol,
                enableCursor = enableCursor,
                borderAnimationDuration = borderAnimationDuration
            )
        }

        companion object {
            private const val EMPTY_CURSOR = ' '
        }
    }
}