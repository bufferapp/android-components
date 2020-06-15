package org.buffer.android.components

import android.buffer.org.ui_kit.R
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.view_item_selection_message_sheet.view.list_item_selection_sheet_options
import kotlinx.android.synthetic.main.view_item_selection_message_sheet.view.text_item_selection_message
import kotlinx.android.synthetic.main.view_item_selection_message_sheet.view.text_item_selection_title
import kotlinx.android.synthetic.main.view_simple_alert_sheet.view.*

object BottomSheetFactory {

    fun createTextItemSelectionBottomSheet(
            context: Context,
            @StringRes titleResource: Int? = null,
            adapter: ArrayAdapter<*>,
            listener: AdapterView.OnItemClickListener,
            footerView: View? = null
    ): BottomSheetDialog {
        return createTextItemSelectionBottomSheet(context, titleResource, messageResource = null,
                adapter = adapter, listener = listener, footerView = footerView)
    }

    fun createTextItemSelectionBottomSheet(
            context: Context,
            @StringRes titleResource: Int? = null,
            @StringRes messageResource: Int? = null,
            adapter: ArrayAdapter<*>,
            listener: AdapterView.OnItemClickListener,
            footerView: View? = null
    ): BottomSheetDialog {
        val title = titleResource?.let { context.getString(titleResource) } ?: kotlin.run { null }
        val message = messageResource?.let { context.getString(messageResource) }
                ?: kotlin.run { null }
        return createTextItemSelectionBottomSheet(context, title, message, adapter, listener,
                footerView)
    }

    fun createTextItemSelectionBottomSheet(
            context: Context,
            title: String? = null,
            message: String? = null,
            adapter: ArrayAdapter<*>,
            listener: AdapterView.OnItemClickListener,
            footerView: View? = null
    ): BottomSheetDialog {
        return BottomSheetDialog(context, R.style.BottomSheetDialog).apply {
            val view = layoutInflater.inflate(R.layout.view_item_selection_message_sheet, null)
                    as LinearLayout
            title?.let {
                view.text_item_selection_title.text = title
            } ?: run {
                view.text_item_selection_title.visibility = View.GONE
            }
            message?.let {
                view.text_item_selection_message.text = message
            } ?: run {
                view.text_item_selection_message.visibility = View.GONE
            }
            view.list_item_selection_sheet_options.adapter = adapter
            view.list_item_selection_sheet_options.onItemClickListener = listener
            footerView?.let { view.addView(footerView) }
            setContentView(view)
        }
    }

    fun createSimpleAlertStyleBottomSheet(
            context: Context,
            @StringRes title: Int,
            @StringRes message: Int,
            @StringRes positiveButtonText: Int,
            @StringRes neutralButtonText: Int,
            @DrawableRes icon: Int,
            postiveListener: View.OnClickListener? = null,
            neutralListener: View.OnClickListener? = null

    ) = BottomSheetDialog(context, R.style.BottomSheetDialog).apply {
        val view = layoutInflater.inflate(R.layout.view_simple_alert_sheet, null)

        view.icon.setImageDrawable(ContextCompat.getDrawable(context, icon))
        view.title.text = context.getString(title)
        view.message.text = context.getString(message)
        view.button_positive.apply {
            text = context.getString(positiveButtonText)
            setOnClickListener(postiveListener)
        }
        view.button_neutral.apply {
            text = context.getString(neutralButtonText)
            setOnClickListener(neutralListener)
        }
        setContentView(view)
    }
}
