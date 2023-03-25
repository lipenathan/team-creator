package com.github.lipenathan.teamcreator.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.services.persistence.local.AppDataBase
import kotlinx.coroutines.launch

class PlayerRegisterViewModel(application: Application) : BaseViewModel(application) {

    private val _saved = SingleLiveEvent<Boolean>()
    val saved: LiveData<Boolean> = _saved

    private val dataBase: AppDataBase

    init {
        dataBase = Room.databaseBuilder(application, AppDataBase::class.java, "team-creator-database").build()
    }

    fun savePlayer(player: Player) {
        val playerDB = getPlayerDB()
        viewModelScope.launch(exceptionsHandler) {
            playerDB.save(player)
            _saved.value = true
        }
    }

    private fun getPlayerDB() = dataBase.playerDao()
}