package com.github.lipenathan.teamcreator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.services.persistence.local.AppDataBase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PlayerListViewModel(application: Application) : AndroidViewModel(application) {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> = _players

    private val dataBase: AppDataBase

    init {
        dataBase = Room.databaseBuilder(application, AppDataBase::class.java, "team-creator-database").build()
    }

    fun getAll() {
        val playerDB = getPlayerDB()
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            _error.value = throwable.message
        }
        viewModelScope.launch(exceptionHandler) {
            _players.value = playerDB.getAll()
        }
    }

    private fun getPlayerDB() = dataBase.playerDao()
}