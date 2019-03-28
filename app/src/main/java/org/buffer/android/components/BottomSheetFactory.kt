package org.buffer.android.components

import android.buffer.org.ui_kit.R
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.view_item_selection_message_sheet.view.list_item_selection_sheet_options
import kotlinx.android.synthetic.main.view_item_selection_message_sheet.view.text_item_selection_message
import kotlinx.android.synthetic.main.view_item_selection_message_sheet.view.text_item_selection_title

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
}