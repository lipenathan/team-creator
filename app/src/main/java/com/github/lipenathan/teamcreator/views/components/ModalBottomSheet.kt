package com.github.lipenathan.teamcreator.views.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.ModalBottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet(val list: List<String>, val itemClickedCallback: (String)-> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: ModalBottomSheetContentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ModalBottomSheetContentBinding.inflate(inflater, container, false)
        val adapter = PlayerPositionArrayAdaper(requireContext(),list) {
            this.dismiss()
            itemClickedCallback(it)
        }
        binding.items.adapter = adapter
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}