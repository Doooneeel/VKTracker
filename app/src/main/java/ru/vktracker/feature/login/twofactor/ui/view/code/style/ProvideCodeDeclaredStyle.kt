package ru.vktracker.feature.login.twofactor.ui.view.code.style

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import ru.vktracker.R.styleable.*
import ru.vktracker.core.ui.view.common.ProvideDeclaredStyle
import ru.vktracker.core.ui.view.common.ProvideDeclaredStyle.*

/**
 * @author Danil Glazkov on 06.07.2023, 11:15
 */
interface ProvideCodeDeclaredStyle : ProvideDeclaredStyle<CodeViewDeclaredStyle> {

    object Default : ProvideCodeDeclaredStyle {
        private val style = CodeViewDeclaredStyle(
            codeLength = 4,
            itemsSpacing = 8
        )
        override fun style(context: Context) = style
    }

    class Base(attributeSet: AttributeSet) : Abstract<CodeViewDeclaredStyle>(attributeSet),
        ProvideCodeDeclaredStyle
    {
        override val styleRes: IntArray = CodeConfirmationView

        override fun TypedArray.style(context: Context): CodeViewDeclaredStyle {
            val defaultStyle = Default.style(context)

            return CodeViewDeclaredStyle(
                codeLength = getInt(CodeConfirmationView_codeLength, defaultStyle.codeLength),
                itemsSpacing = getDimensionPixelSize(CodeConfirmationView_codeItemsSpacing,
                    defaultStyle.itemsSpacing
                )
            )
        }
    }
}