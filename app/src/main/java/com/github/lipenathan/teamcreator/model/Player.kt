package com.github.lipenathan.teamcreator.model

import com.github.lipenathan.teamcreator.model.Position.*

data class Player(var name: String, var position: Position, var secondPosition: Position, var rate: Int): java.io.Serializable
{
    var id: Long? = null

    fun isDefensive(): Boolean {
        return if (position == ZAGUEIRO) true
        else if (isLateral() && (secondPosition == VOLANTE || secondPosition == ZAGUEIRO)) true
        else if (position == VOLANTE && (isLateral() || secondPosition == ZAGUEIRO)) true
        else if (position == MEIO_CAMPO && (secondPosition == VOLANTE || isLateral() || secondPosition == ZAGUEIRO)) true
        else false
    }

    private fun isLateral() = position == LATERAL_DIREITO || position == LATERAL_ESQUERDO
}