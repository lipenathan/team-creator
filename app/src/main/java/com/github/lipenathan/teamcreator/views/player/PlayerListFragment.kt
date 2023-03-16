package com.github.lipenathan.teamcreator.views.player

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.FragmentPlayersListBinding
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.services.persistence.memory.PlayerMemoryDataBase
import com.github.lipenathan.teamcreator.viewmodel.PlayerListViewModel
import com.github.lipenathan.teamcreator.views.BaseFragment
import com.github.lipenathan.teamcreator.views.GameConfigurationFragment
import com.github.lipenathan.teamcreator.views.components.adapter.ListPlayerAdapter
import com.github.lipenathan.teamcreator.views.player.PlayerActivty.Navigation.BACK_STACK
import com.github.lipenathan.teamcreator.views.player.PlayerActivty.Navigation.CONTAINER
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PlayerListFragment : BaseFragment(R.layout.fragment_players_list) {

    private lateinit var binding: FragmentPlayersListBinding
    private lateinit var viewModel: PlayerListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PlayerListViewModel::class.java)
        setObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlayersListBinding.inflate(inflater, container, false)
        setListeners()
        viewModel.getAll()
        return binding.root
    }


    private fun setObservers() {
        viewModel.apply {
            error.observe(this@PlayerListFragment) {
                val dialog = MaterialAlertDialogBuilder(this@PlayerListFragment.requireContext())
                dialog.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                    requireActivity().supportFragmentManager.popBackStack()
                }
                dialog.setMessage(it)
                dialog.show()
            }
            players.observe(this@PlayerListFragment) {
                setList(it)
            }
        }
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

    private fun setList(players: List<Player>) {
        if (!players.isEmpty()) {
            val adapter = ListPlayerAdapter()
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = VERTICAL
            binding.playersList.layoutManager = layoutManager
            binding.playersList.adapter = adapter
            adapter.update(players)
        }
    }
}