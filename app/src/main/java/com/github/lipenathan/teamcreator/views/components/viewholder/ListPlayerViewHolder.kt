package com.github.lipenathan.teamcreator.views.components.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.ListItemPlayerBinding
import com.github.lipenathan.teamcreator.model.Player

class ListPlayerViewHolder(val binding: ListItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context
    private val stars: List<ImageView> get() = listOf(binding.star1, binding.star2, binding.star3)

    fun bind(player: Player, last: Boolean) {
        binding.apply {
            player.also {
                textName.text = it.name
                textMainPosition.text = "${it.position?.flag} /"
                textSecondPosition.text = it.secondPosition?.flag
                setStarRate(it.rate)
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