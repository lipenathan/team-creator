package com.github.lipenathan.teamcreator.services.persistence.memory

import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.services.persistence.BasicDAO

class PlayerMemoryDataBase : BasicDAO<Player> {

    override fun save(o: Player) {
        if (o.id == null) {
            o.id = ID++
        }
        players.add(o)
    }

    override fun getById(id: Long): Player? {
        return players.find { it.id == id }
    }

    override fun getAll(): List<Player> {
        return players
    }

    companion object {
        private var ID = 1L
        private val players = mutableListOf<Player>()
    }
}