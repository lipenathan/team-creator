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

class PlayerRegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _saved = MutableLiveData<Boolean>()
    val saved: LiveData<Boolean> = _saved

    private val dataBase: AppDataBase

    init {
        dataBase = Room.databaseBuilder(application, AppDataBase::class.java, "team-creator-database").build()
    }

    fun savePlayer(player: Player) {
        val playerDB = getPlayerDB()
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            _error.value = throwable.message
        }
        viewModelScope.launch(exceptionHandler) {
            playerDB.save(player)
            _saved.value = true
        }
    }

    private fun getPlayerDB() = dataBase.playerDao()
}