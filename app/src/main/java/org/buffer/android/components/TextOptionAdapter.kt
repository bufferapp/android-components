package org.buffer.android.components

import android.buffer.org.ui_kit.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

open class TextOptionAdapter(
    context: Context,
    private val values: Array<String>
) : ArrayAdapter<String>(context, R.layout.item_share_option, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val optionViewHolder: OptionViewHolder
        var shareOptionRow = convertView
        if (shareOptionRow == null) {
            shareOptionRow = View.inflate(context, R.layout.item_share_option, null)
            optionViewHolder = OptionViewHolder(shareOptionRow)
            shareOptionRow.tag = optionViewHolder
        } else {
            optionViewHolder = shareOptionRow.tag as OptionViewHolder
        }
        optionViewHolder.optionText.text = values[position]
        return shareOptionRow!!
    }

    internal class OptionViewHolder(itemView: View) {
        var optionText: TextView = itemView.findViewById<View>(R.id.text_option) as TextView
    }
}