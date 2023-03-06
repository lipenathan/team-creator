package com.github.lipenathan.teamcreator.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.FragmentGameConfigurationBinding

class GameConfigurationFragment: BaseFragment(R.layout.fragment_game_configuration) {

    private lateinit var binding: FragmentGameConfigurationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameConfigurationBinding.inflate(inflater, container, false)
        return binding.root
    }
}