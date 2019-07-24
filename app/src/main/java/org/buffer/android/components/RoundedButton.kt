package org.buffer.android.components

import android.buffer.org.ui_kit.R
import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import android.view.Gravity.CENTER_HORIZONTAL

class RoundedButton @JvmOverloads constructor(context: Context,
                                              attrs: AttributeSet? = null,
                                              defStyleAttr: Int = 0)
    : AppCompatButton(context, attrs, defStyleAttr) {

    init {
        setBackground(attrs)
        gravity = CENTER_HORIZONTAL
        isClickable = true
        typeface = Typeface.DEFAULT_BOLD
    }

    private fun setBackground(attrs: AttributeSet? = null) {
        val style = ButtonStyle.fromIdentifier(getStyleIdentifierFromStyleableAttributes(attrs))
        when (style) {
            ButtonStyle.DARK -> {
                setTextColor(ContextCompat.getColor(context,
                        R.color.selector_dark_button_text))
                setBackgroundResource(R.drawable.button_bfr_round_dark)
            }
            ButtonStyle.LIGHT -> {
                setTextColor(ContextCompat.getColorStateList(context,
                        R.color.selector_light_button_text))
                setBackgroundResource(R.drawable.button_bfr_round_light)
            }
            ButtonStyle.CLEAR -> {
                setTextColor(ContextCompat.getColor(context,
                        R.color.selector_clear_button_text))
                setBackgroundResource(R.drawable.button_bfr_round_white)
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