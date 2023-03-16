package com.github.lipenathan.teamcreator.services.persistence.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.model.Team

@Database(entities = [Player::class, Team::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun teamDao(): TeamDao
}