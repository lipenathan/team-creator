package com.github.lipenathan.teamcreator.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.services.persistence.local.AppDataBase
import kotlinx.coroutines.launch

class GameConfigurationViewModel(application: Application) : BaseViewModel(application) {

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> = _players

    var vTeamsNumber: Int = 0
    var vPlayersPerTeam: Int = 0

    private val database: AppDataBase

    init {
        database = Room.databaseBuilder(getApplication(), AppDataBase::class.java, "team-creator-database").build()
    }

    fun getAllPlayers() {
        val playerDao = getPlayerDao()
        viewModelScope.launch(exceptionsHandler) {
            _players.value = playerDao.getAll()
        }
    }

    private fun getPlayerDao() = database.playerDao()
}