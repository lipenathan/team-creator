package com.github.lipenathan.teamcreator.services.persistence.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.github.lipenathan.teamcreator.model.Team

@Dao
interface TeamDao {

    @Transaction
    @Query("select * from team")
    suspend fun getAll(): List<Team>

    @Insert
    suspend fun save(vararg team: Team)
}