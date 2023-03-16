package com.github.lipenathan.teamcreator.views.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.FragmentTeamsListBinding
import com.github.lipenathan.teamcreator.databinding.ItemTeamListBinding
import com.github.lipenathan.teamcreator.model.Team
import com.github.lipenathan.teamcreator.views.BaseFragment
import com.github.lipenathan.teamcreator.views.components.adapter.ListPlayerAdapter

class TeamsListFragment : BaseFragment(R.layout.fragment_teams_list) {

    private lateinit var binding: FragmentTeamsListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTeamsListBinding.inflate(inflater, container, false)
        val list = arguments?.getSerializable(TEAMS) as Array<Team>
        setList(list)
        return binding.root
    }

    private fun setList(list: Array<Team>) {
        list.forEachIndexed { index, team ->
            val layoutTeam = ItemTeamListBinding.inflate(LayoutInflater.from(requireContext()))
            val adapter = ListPlayerAdapter()
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = VERTICAL
            layoutTeam.listTeam.layoutManager = layoutManager
            layoutTeam.listTeam.adapter = adapter
            binding.viewTeams.addView(layoutTeam.root)
            layoutTeam.textTeamNumber.setText("${index + 1}")
            adapter.update(team.players)
        }
    }

    companion object {
        const val TEAMS = "teams_intent"
    }
}