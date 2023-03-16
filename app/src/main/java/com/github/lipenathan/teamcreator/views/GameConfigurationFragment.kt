package com.github.lipenathan.teamcreator.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.FragmentGameConfigurationBinding
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.model.Position
import com.github.lipenathan.teamcreator.model.Team
import com.github.lipenathan.teamcreator.model.getNext
import com.github.lipenathan.teamcreator.services.persistence.memory.PlayerMemoryDataBase
import com.github.lipenathan.teamcreator.views.player.PlayerActivty.Navigation.CONTAINER
import com.github.lipenathan.teamcreator.views.team.TeamsListFragment
import com.github.lipenathan.teamcreator.views.team.TeamsListFragment.Companion.TEAMS
import com.google.android.material.snackbar.Snackbar

class GameConfigurationFragment : BaseFragment(R.layout.fragment_game_configuration) {

    private lateinit var binding: FragmentGameConfigurationBinding
    private val playersDb = PlayerMemoryDataBase()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameConfigurationBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonCreateTeams.setOnClickListener {
            if (binding.playersPerTeamIncrementer.value <= 0) {
                Snackbar.make(binding.root, "Número de jogadores por time precisa ser maior que 0", Snackbar.LENGTH_LONG).show()
            } else if (binding.teamsNumberIncrementer.value <= 0) {
                Snackbar.make(binding.root, "Número de times precisa ser maior que 0", Snackbar.LENGTH_LONG).show()
            } else {
                val teams = createSimpleTeams()
                val bundle = Bundle()
                bundle.putSerializable(TEAMS, teams)
                val fragment = TeamsListFragment()
                fragment.arguments = bundle
                navigateReplacing(CONTAINER, fragment)
            }
        }
    }

    private fun createTeams() {
        val players = playersDb.getAll()
        val ppTeam = binding.playersPerTeamIncrementer.value
        val teamsNumber = binding.teamsNumberIncrementer.value
        val teams = List(teamsNumber) {
            Team()
        }
        //ver se a qtd de goleiros bate com a qtd de times
        //se sim dividir um pra cada, independente do rate
        //caso não, dividir para os primeiros times
        val goalKeepers = players.filter { it.position == Position.GOLEIRO }
        teams.forEachIndexed { index, team ->
            goalKeepers.getOrNull(index)?.let {
                team.players.add(it)
            }
        }
        val defensives = players.filter { it.isDefensive() }
        val backs = defensives.filter { it.position == Position.ZAGUEIRO }
        backs.forEachIndexed { index, back ->
            teams.getNext(index).players.add(back)
        }

        //selecionar jogadores de defesa
        //  zag
        //  lateral
        //  volante defensivo
        //selecionar jogadores ofensivos
        //  lateral
        //  volante
        //  meio
        //  atacante
        //  pontas
        //  centro avantes
        //obs. sempre verificar rating
        //final
        //caso sobre jogadores, criar uma lista/time de jogadores que sobraram
    }

    /**
     * Separa times baseado apenas no rating do jogador e não por posição (exceto goleiro).
     */
    private fun createSimpleTeams(): Array<Team> {
        val players = playersDb.getAll().toMutableList()
        val ppTeam = binding.playersPerTeamIncrementer.value
        val teamsNumber = binding.teamsNumberIncrementer.value
        val teams = Array(teamsNumber) {
            Team()
        }
        val goalKeepers = players.filter { it.position == Position.GOLEIRO }
        teams.forEachIndexed { index, team ->
            goalKeepers.getOrNull(index)?.let {
                team.players.add(it)
            }
        }
        players.removeAll(goalKeepers)
        players.forEachIndexed { index, player ->
            teams.getNext(index).players.add(player)
        }
        return teams
    }

    private fun compareRating(team1: Team, team2: Team): Float {
        return team1.rating - team2.rating
    }

    private fun getLowestRate(players: List<Player>): Player {
        return players.stream().min { p1, p2 -> p1.rate - p2.rate }.get()
    }

    private fun getHighestRate(players: List<Player>): Player {
        return players.stream().min { p1, p2 -> p2.rate - p1.rate }.get()
    }
}