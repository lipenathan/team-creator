package com.github.lipenathan.teamcreator.views.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.lipenathan.teamcreator.databinding.ModalBottomSheetPlayersContentBinding
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.views.components.adapter.ListPlayerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayersModalBottomSheet(val list: List<Player>, val itemClickedCallback: (Player) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: ModalBottomSheetPlayersContentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ModalBottomSheetPlayersContentBinding.inflate(inflater, container, false)
        val adapter = ListPlayerAdapter(ListType.TEAMS) {
            this.dismiss()
            itemClickedCallback(it)
        }
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
//        binding.items.setHasFixedSize(true)
//        binding.items.isNestedScrollingEnabled = false
//        binding.items.isScrollContainer = false
        binding.items.layoutManager = layoutManager
        binding.items.adapter = adapter
        adapter.update(list)
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}