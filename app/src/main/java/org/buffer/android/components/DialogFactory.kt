package org.buffer.android.components

import android.app.ProgressDialog
import android.buffer.org.ui_kit.R
import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import org.buffer.android.counterview.CounterView

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogFactory {

    fun createSimpleYesNoDialog(
            context: Context,
            title: String,
            message: String? = null,
            positive: String,
            negative: String,
            positiveListener: DialogInterface.OnClickListener? = null,
            negativeListener: DialogInterface.OnClickListener? = null
    ): AlertDialog {
        val alertDialog = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setPositiveButton(positive, positiveListener)
                .setNegativeButton(negative, negativeListener)
        if (!message.isNullOrBlank()) {
            alertDialog.setMessage(message)
        }
        return alertDialog.create()
    }

    fun createSimpleYesNoDialog(
            context: Context,
            title: String,
            message: String
    ): AlertDialog {
        val alertDialog = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null)
        return alertDialog.create()
    }

    fun createSimpleYesNoDialog(
            context: Context,
            title: String,
            message: String,
            positive: String
    ): AlertDialog {
        val alertDialog = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, null)
        return alertDialog.create()
    }

    fun createSimpleYesNoDialog(
            context: Context,
            @StringRes title: Int,
            @StringRes message: Int,
            @StringRes positive: Int,
            @StringRes negative: Int,
            positiveListener: DialogInterface.OnClickListener? = null,
            negativeListener: DialogInterface.OnClickListener? = null
    ): AlertDialog {
        val alertDialog = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, positiveListener)
                .setNegativeButton(negative, negativeListener)
        return alertDialog.create()
    }

    fun createImageHeaderDialog(
            context: Context,
            @StringRes title: Int,
            @StringRes message: Int,
            @StringRes positive: Int,
            @StringRes neutral: Int,
            @DrawableRes headerImage: Int,
            @ColorRes headerColor: Int,
            positiveListener: View.OnClickListener? = null,
            neutralListener: View.OnClickListener? = null
    ): AlertDialog {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_image_dialog, null)
        (view.findViewById<View>(R.id.image_header) as ImageView).setImageDrawable(
                ContextCompat.getDrawable(context, headerImage))
        view.findViewById<View>(R.id.container_image).setBackgroundColor(
                ContextCompat.getColor(context, headerColor))
        view.findViewById<View>(R.id.button_positive).setOnClickListener(positiveListener)
        view.findViewById<View>(R.id.button_neutral).setOnClickListener(neutralListener)
        (view.findViewById<View>(R.id.text_title) as TextView).text = context.getString(title)
        (view.findViewById<View>(R.id.text_message) as TextView).text = context.getString(message)
        (view.findViewById<View>(R.id.button_positive) as TextView).text = context.getString(positive)
        (view.findViewById<View>(R.id.button_neutral) as TextView).text = context.getString(neutral)
        return MaterialAlertDialogBuilder(context).setView(view).create()
    }

    fun createInfoDialog(
            context: Context,
            @StringRes titleResource: Int,
            @StringRes messageResource: Int,
            @StringRes positiveResource: Int,
            @StringRes negativeResource: Int,
            @StringRes neutralResource: Int,
            positiveListener: DialogInterface.OnClickListener? = null,
            negativeListener: DialogInterface.OnClickListener? = null,
            neutralListener: DialogInterface.OnClickListener
    ): AlertDialog {
        return MaterialAlertDialogBuilder(context)
                .setTitle(titleResource)
                .setMessage(messageResource)
                .setPositiveButton(positiveResource, positiveListener)
                .setNegativeButton(negativeResource, negativeListener)
                .setNeutralButton(neutralResource, neutralListener)
                .create()
    }

    fun createSimpleInfoDialog(
        context: Context,
        title: String,
        message: String,
        neutral: String
    ): AlertDialog {
        return createSimpleInfoDialog(context, title, message, neutral)
    }

    fun createSimpleInfoDialog(
            context: Context,
            @StringRes titleResource: Int,
            @StringRes messageResource: Int,
            @StringRes neutralResource: Int
    ): AlertDialog {
        return createSimpleInfoDialog(context, context.getString(titleResource),
                context.getString(messageResource), context.getString(neutralResource))
    }

    fun createSimpleInfoDialog(
            context: Context,
            @StringRes titleResource: Int,
            @StringRes messageResource: Int,
            @StringRes neutralResource: Int,
            onClickListener: DialogInterface.OnClickListener? = null
    ): AlertDialog {
        return createSimpleInfoDialog(context, context.getString(titleResource),
                context.getString(messageResource), context.getString(neutralResource),
                onClickListener)
    }

    fun createSimpleInfoDialog(
            context: Context,
            title: String,
            message: CharSequence,
            buttonText: String,
            onClickListener: DialogInterface.OnClickListener? = null
    ): AlertDialog {
        val alertDialog = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonText, onClickListener)
        return alertDialog.create()
    }

    fun createSimpleLearnMoreDialog(
            context: Context,
            @StringRes title: Int,
            @StringRes message: Int,
            @StringRes positiveButtonText: Int,
            @StringRes neutralButtonText: Int,
            positiveClickListener: DialogInterface.OnClickListener? = null,
            neutralClickListener: DialogInterface.OnClickListener? = null
    ): AlertDialog {
        val alertDialog = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, positiveClickListener)
                .setNeutralButton(neutralButtonText, neutralClickListener)
        return alertDialog.create()

    }

    fun createProgressDialog(
            context: Context,
            message: String
    ): ProgressDialog {
        return ProgressDialog(context).apply { setMessage(message) }
    }

    fun createSimpleListDialog(
            context: Context,
            items: Array<String>,
            onClickListener: DialogInterface.OnClickListener? = null
    ): AlertDialog {
        return MaterialAlertDialogBuilder(context).setItems(items, onClickListener).create()
    }

    fun createProgressDialog(
            context: Context,
            @StringRes messageResource: Int
    ): ProgressDialog {
        return createProgressDialog(context, context.getString(messageResource))
    }

    fun createProgressDialog(
            context: Context,
            @StringRes messageResource: Int,
            listener: ProgressListener? = null
    ): ProgressDialog {
        val progressDialog = createProgressDialog(context,
                context.getString(messageResource))
        progressDialog.setOnDismissListener { listener?.onDismiss() }
        progressDialog.setOnCancelListener { listener?.onCancel() }
        progressDialog.setCancelable(true)
        progressDialog.setCanceledOnTouchOutside(true)
        return progressDialog
    }

    fun createUrlInputDialog(
            context: Context,
            @StringRes titleResource: Int,
            @StringRes positive: Int,
            @StringRes negative: Int,
            inputListener: InputListener,
            defaultText: String
    ): AlertDialog {
        return getSimpleInputDialogBuilder(context, titleResource, positive,
                negative, inputListener, defaultText, InputType.TYPE_TEXT_VARIATION_URI).create()
    }

    private fun getSimpleInputDialogBuilder(
            context: Context,
            @StringRes titleResource: Int,
            @StringRes positive: Int,
            @StringRes negative: Int,
            inputListener: InputListener,
            defaultText: String?,
            inputType: Int
    ): AlertDialog.Builder {
        val builder = MaterialAlertDialogBuilder(context)
        builder.setTitle(titleResource)

        val input = EditText(context)
        input.inputType = inputType
        if (inputType == InputType.TYPE_TEXT_FLAG_MULTI_LINE) {
            input.isSingleLine = false
            input.maxLines = 8
        }
        if (defaultText != null) input.setText(defaultText)

        val padding = DisplayMetricsUtil.dpToPx(16)
        builder.setView(input, padding, 0, padding, 0)
        builder.setPositiveButton(positive) { dialog, which -> inputListener.onTextInput(input.text.toString()) }
        builder.setNegativeButton(negative) { dialog, which -> dialog.cancel() }
        return builder
    }

    fun showLimitedLengthInputDialog(
            context: Context, maxLength: Int,
            @StringRes titleResource: Int,
            @StringRes messageResource: Int,
            @StringRes positive: Int,
            @StringRes negative: Int,
            inputListener: InputListener? = null,
            defaultText: String? = null
    ): AlertDialog {
        val builder = MaterialAlertDialogBuilder(context)
                .setTitle(titleResource)
                .setMessage(messageResource)

        val layout = LayoutInflater.from(context)
                .inflate(R.layout.view_limited_length_input, null)
        val editText = layout.findViewById<EditText>(R.id.input)
        val counterView = layout.findViewById<CounterView>(R.id.view_counter)
        if (defaultText != null) editText.setText(defaultText)
        counterView.charactersRemainingUntilCounterDisplay = (maxLength * 0.15).toInt()
        counterView.counterMaxLength = maxLength
        counterView.attachToEditText(editText)

        builder.setView(layout)
                .setPositiveButton(positive) { _, _ ->
                    inputListener?.onTextInput(editText.text.toString())
                }
                .setNegativeButton(negative) { dialog, _ -> dialog.cancel() }
        val dialog = builder.create()
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                        editText.text.length <= maxLength
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {}
        })
        return dialog
    }

    fun createCheckboxDialog(
            context: Context,
            @StringRes title: Int,
            @StringRes message: Int,
            @StringRes positive: Int,
            @StringRes neutral: Int,
            @StringRes checkbox: Int,
            clickListener: ActionListener? = null
    ): MaterialAlertDialogBuilder {
        val checkboxView = LayoutInflater.from(context).inflate(R.layout.checkbox, null)
        val dontShowAgain = checkboxView.findViewById<CheckBox>(R.id.skip)
        dontShowAgain.setText(checkbox)

        return MaterialAlertDialogBuilder(context)
                .setView(checkboxView)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive) { dialog, which ->
                    clickListener?.onPositiveButtonSelected(dontShowAgain.isChecked) }
                .setNeutralButton(neutral) { dialog, which ->
                    clickListener?.onNeutralButtonSelected(dontShowAgain.isChecked) }
    }


    interface InputListener {
        fun onTextInput(text: String)
    }

    interface ActionListener {

        fun onPositiveButtonSelected(shouldNotShowAgain: Boolean)

        fun onNeutralButtonSelected(shouldNotShowAgain: Boolean)
    }

    interface ProgressListener {
        fun onCancel()

        fun onDismiss()
    }
}

