package org.buffer.android.components

import android.buffer.org.ui_kit.R
import android.content.Context
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity.CENTER
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.button.MaterialButton

class RoundedButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : MaterialButton(ContextThemeWrapper(context, R.style.RoundedButtonStyle), attrs, defStyleAttr) {

    init {
        setBackground(attrs)
        gravity = CENTER
        isClickable = true
    }

    private fun setBackground(attrs: AttributeSet? = null) {
        when (ButtonStyle.fromIdentifier(getStyleIdentifierFromStyleableAttributes(attrs))) {
            ButtonStyle.DARK -> {
                setTextColor(ContextCompat.getColorStateList(context,
                    R.color.selector_dark_button_text))
                backgroundTintList = ContextCompat.getColorStateList(context,
                    R.color.selector_dark_button_background)
            }
            ButtonStyle.LIGHT -> {
                setTextColor(ContextCompat.getColorStateList(context,
                    R.color.selector_light_button_text))
                backgroundTintList = ContextCompat.getColorStateList(context,
                    R.color.selector_light_button_background)
            }
            ButtonStyle.CLEAR -> {
                setTextColor(ContextCompat.getColorStateList(context,
                    R.color.selector_clear_button_text))
                backgroundTintList = ContextCompat.getColorStateList(context,
                    R.color.selector_clear_button_background)
            }
        }
    }

    private fun getStyleIdentifierFromStyleableAttributes(attrs: AttributeSet?): Int {
        val styledAttributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RoundedButtonStyles,
            0, 0)

        if (styledAttributes.hasValue(R.styleable.RoundedButtonStyles_bufferButtonStyle)) {
            return styledAttributes.getInt(R.styleable.RoundedButtonStyles_bufferButtonStyle, 0)
        }
        return 0
    }

    private enum class ButtonStyle {
        DARK, LIGHT, CLEAR;

        companion object {
            fun fromIdentifier(identifier: Int): ButtonStyle {
                return when (identifier) {
                    0 -> DARK
                    1 -> LIGHT
                    2 -> CLEAR
                    else -> DARK
                }
            }
        }
    }


}