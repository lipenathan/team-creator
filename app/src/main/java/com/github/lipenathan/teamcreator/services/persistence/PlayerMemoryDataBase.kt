package com.github.lipenathan.teamcreator.services.persistence

import com.github.lipenathan.teamcreator.model.Player

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