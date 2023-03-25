package com.github.lipenathan.teamcreator.views.components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.lipenathan.teamcreator.databinding.ListItemPlayerBinding
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.views.components.ListType
import com.github.lipenathan.teamcreator.views.components.viewholder.ListPlayerViewHolder

class ListPlayerAdapter(val type: ListType = ListType.ALL, val itemClickedCallback: ((Player) -> Unit)? = null) : RecyclerView.Adapter<ListPlayerViewHolder>() {

    private var list = listOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPlayerViewHolder {
        val binding = ListItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPlayerViewHolder(binding, type, itemClickedCallback)
    }

    override fun onBindViewHolder(holder: ListPlayerViewHolder, position: Int) {
        holder.bind(list[position], position == list.count() - 1)
    }

    override fun getItemCount() = list.count()

    fun update(list: List<Player>) {
        this.list = list
        notifyDataSetChanged()
    }
}