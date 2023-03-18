package com.github.lipenathan.teamcreator.views.components.viewholder

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.ListItemPlayerBinding
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.views.components.ListType


class ListPlayerViewHolder(val binding: ListItemPlayerBinding,val type: ListType) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context
    private val stars: List<ImageView> get() = listOf(binding.star1, binding.star2, binding.star3)

    fun bind(player: Player, last: Boolean) {
        binding.apply {
            player.also {
                textName.text = it.name
                textMainPosition.text = "${it.position?.flag} /"
                textSecondPosition.text = it.secondPosition?.flag
                setStarRate(it.rate)
                divider.visibility = if (last) GONE else VISIBLE
                when(type) {
                    ListType.ALL -> {
                        if (last) {
                            val newLayoutParams = root.layoutParams as RecyclerView.LayoutParams
                            newLayoutParams.setMargins(0,0,0,32)
                            root.layoutParams=  newLayoutParams
                        }
                    }
                    ListType.TEAMS ->{}
                }
            }
        }
    }

    fun setStarRate(rate: Int) {
        for (i in 0..rate - 1) {
            stars[i].setImageDrawable(context.getDrawable(R.drawable.star))
        }
        for (i in rate..2) {
            stars[i].setImageDrawable(context.getDrawable(R.drawable.star_empty))
        }
    }
}