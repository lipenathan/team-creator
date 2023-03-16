package com.github.lipenathan.teamcreator.services.persistence.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.lipenathan.teamcreator.model.Player

@Dao
interface PlayerDao {

    @Query("select * from player")
    suspend fun getAll(): List<Player>

    @Query("select * from player where player_id = :userId")
    suspend fun getById(userId: Long): Player?

    @Insert
    suspend fun save(vararg users: Player)

}