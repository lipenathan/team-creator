package com.github.lipenathan.teamcreator.model

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class TeamWithPlayers(
    @Embedded val team: Team,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "player_team"
    )
    val players: List<Player>
) : Serializable