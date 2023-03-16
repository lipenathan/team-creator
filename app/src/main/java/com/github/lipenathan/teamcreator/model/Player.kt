package com.github.lipenathan.teamcreator.model

import androidx.room.*
import com.github.lipenathan.teamcreator.model.Position.*
import com.github.lipenathan.teamcreator.services.persistence.Converters


@Entity
//    (
//    foreignKeys = [
//        ForeignKey(
//            entity = Team::class,
//            parentColumns = ["teamId"],
//            childColumns = ["teamId"],
//            onDelete = ForeignKey.CASCADE,
//            onUpdate = ForeignKey.CASCADE
//        )
//    ]
//)
data class Player(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "rate") var rate: Int
) : java.io.Serializable {
    constructor(name: String, position: Position, secondPosition: Position, rate: Int) : this(name, rate) {
        this.position = position
        this.secondPosition = secondPosition
    }

    @PrimaryKey
    @ColumnInfo(name = "player_id")
    var id: Long? = null

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "position")
    var position: Position? = null

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "second_position")
    var secondPosition: Position? = null


    @ColumnInfo(name = "player_team", index = true)
    var teamId: Long? = null

    fun isDefensive(): Boolean {
        return if (position == ZAGUEIRO) true
        else if (isLateral() && (secondPosition == VOLANTE || secondPosition == ZAGUEIRO)) true
        else if (position == VOLANTE && (isLateral() || secondPosition == ZAGUEIRO)) true
        else if (position == MEIO_CAMPO && (secondPosition == VOLANTE || isLateral() || secondPosition == ZAGUEIRO)) true
        else false
    }

    private fun isLateral() = position == LATERAL_DIREITO || position == LATERAL_ESQUERDO
}