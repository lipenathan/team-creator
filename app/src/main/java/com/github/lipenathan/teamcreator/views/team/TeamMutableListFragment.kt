package com.github.lipenathan.teamcreator.views.team

import android.os.Bundle
import android.view.Gravity.CENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.FragmentTeamsListBinding
import com.github.lipenathan.teamcreator.databinding.ItemTeamMutableListBinding
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.model.Team
import com.github.lipenathan.teamcreator.viewmodel.GameConfigurationViewModel
import com.github.lipenathan.teamcreator.views.BaseFragment
import com.github.lipenathan.teamcreator.views.components.ListType
import com.github.lipenathan.teamcreator.views.components.ModalBottomSheet
import com.github.lipenathan.teamcreator.views.components.PlayersModalBottomSheet
import com.github.lipenathan.teamcreator.views.components.adapter.ListPlayerAdapter

class TeamMutableListFragment : BaseFragment(R.layout.fragment_teams_list) {

    private lateinit var viewModel: GameConfigurationViewModel
    private lateinit var binding: FragmentTeamsListBinding
    private lateinit var players: MutableList<Player>
    private lateinit var teamAdapters: Array<ListPlayerAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameConfigurationViewModel::class.java)
        setObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTeamsListBinding.inflate(inflater, container, false)
        binding = FragmentTeamsListBinding.inflate(inflater, container, false)
        val list = arguments?.getSerializable(TeamsListFragment.TEAMS) as Array<Team>
        teamAdapters = Array(list.count()) {
            ListPlayerAdapter(ListType.TEAMS)
        }
        viewModel.getAllPlayers()
        setList(list)
        return binding.root
    }

    private fun setObservers() {
        viewModel.apply {
            players.observe(this@TeamMutableListFragment) {
                this@TeamMutableListFragment.players = it.toMutableList()
            }
        }
    }

    private fun setList(list: Array<Team>) {
        list.forEachIndexed { index, team ->
            val layoutTeam = ItemTeamMutableListBinding.inflate(LayoutInflater.from(requireContext()))
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutTeam.listTeam.setHasFixedSize(true)
            layoutTeam.listTeam.isNestedScrollingEnabled = false
            layoutTeam.listTeam.isScrollContainer = false
            layoutTeam.listTeam.layoutManager = layoutManager
            layoutTeam.listTeam.adapter = teamAdapters[index]
            teamAdapters[index].update(team.players)
            layoutTeam.textTeamNumber.setText("${index + 1}")
            layoutTeam.buttonAddPlayer.setOnClickListener {
                val playersBottomSheetDialog = PlayersModalBottomSheet(players) {
                    team.players.add(it)
                    teamAdapters[index].update(team.players)
                    players.remove(it)
                }
                playersBottomSheetDialog.show(requireActivity().supportFragmentManager, ModalBottomSheet.TAG)
            }
            binding.viewTeams.addView(layoutTeam.root)
        }
    }

    private fun emptyListView(view: LinearLayout) {
        view.removeAllViews()
        val text = TextView(requireContext())
        text.text = "Cadastre jogadores neste time!"
        text.gravity = CENTER
        view.addView(text)
    }

    companion object {
        const val TEAMS = "teams_intent"
    }
}