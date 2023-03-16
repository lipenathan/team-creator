package com.github.lipenathan.teamcreator.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Team(@Ignore val players: MutableList<Player> = mutableListOf()) : Serializable {

    @PrimaryKey
    var teamId: Long? = null
    val rating: Float get() = rating()
    val playersNumber: Int get() = players.count()

    private fun rating(): Float {
        return players.sumOf { p -> p.rate }.div(playersNumber.toFloat())
    }

    private fun hasGoalKeeper(): Boolean {
        return players.stream().anyMatch { p -> p.position == Position.GOLEIRO }
    }
}