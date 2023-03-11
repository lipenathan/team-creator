package com.github.lipenathan.teamcreator.views.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.FragmentPlayersListBinding
import com.github.lipenathan.teamcreator.services.persistence.PlayerMemoryDataBase
import com.github.lipenathan.teamcreator.views.BaseFragment
import com.github.lipenathan.teamcreator.views.GameConfigurationFragment
import com.github.lipenathan.teamcreator.views.components.adapter.ListPlayerAdapter
import com.github.lipenathan.teamcreator.views.player.PlayerActivty.Navigation.BACK_STACK
import com.github.lipenathan.teamcreator.views.player.PlayerActivty.Navigation.CONTAINER

class PlayerListFragment : BaseFragment(R.layout.fragment_players_list) {

    private lateinit var binding: FragmentPlayersListBinding
    private val playersDB = PlayerMemoryDataBase()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlayersListBinding.inflate(inflater, container, false)
        setListeners()
        setList()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonAdd.setOnClickListener {
            navigateAdding(CONTAINER, PlayerRegisterFragment(), BACK_STACK, this)
        }
        binding.buttonNextStep.setOnClickListener {
            val fragment = GameConfigurationFragment()
            navigateReplacing(CONTAINER, fragment, BACK_STACK)
        }
    }

    private fun setList() {
        val players = playersDB.getAll()
        val adapter = ListPlayerAdapter()
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = VERTICAL
        binding.playersList.layoutManager = layoutManager
        binding.playersList.adapter = adapter
        adapter.update(players)
    }
}