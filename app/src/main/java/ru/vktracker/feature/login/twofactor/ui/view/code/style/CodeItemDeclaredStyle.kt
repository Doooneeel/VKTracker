package ru.vktracker.feature.login.twofactor.ui.view.code.style

import android.graphics.Typeface
import androidx.annotation.ColorInt
import androidx.annotation.Px

/**
 * @author Danil Glazkov on 06.07.2023, 17:02
 */
class CodeItemDeclaredStyle(
    @Px val width: Int,
    @Px val height: Int,
    @ColorInt val backgroundColor: Int,
    @ColorInt val borderColor: Int,
    @ColorInt val borderColorActive: Int,
    @ColorInt val textColor: Int,
    @Px val borderWidth: Float,
    @Px val textSize: Int,
    val borderCornerRadius: Float,
    val enableCursor: Boolean,
    val typeface: Typeface,
    val cursorSymbol: Char,
    val borderAnimationDuration: Int,
)