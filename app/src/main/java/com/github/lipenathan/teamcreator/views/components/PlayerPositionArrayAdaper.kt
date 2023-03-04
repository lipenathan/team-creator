package com.github.lipenathan.teamcreator.views.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.ListItemPlayerPosition2Binding

class PlayerPositionArrayAdaper(context: Context, val list: List<String>, val itemClickedCallback: (String)-> Unit) : ArrayAdapter<String>(
    context, R.layout.list_item_player_position2,
    R.id.text_position, list
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = list[position]
        val viewHolder: ViewHolder
        if (convertView == null) {
            val binding = ListItemPlayerPosition2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
            viewHolder = ViewHolder(binding.textPosition, binding.divider)
        } else {
            viewHolder = ViewHolder(convertView.findViewById(R.id.text_position), convertView.findViewById(R.id.divider))
        }
        viewHolder.text_position.text = item
        val view = super.getView(position, convertView, parent)
        view.rootView.setOnClickListener {
            itemClickedCallback(item)
        }
        return view
    }

    class ViewHolder(val text_position: TextView, val divider: View)
}