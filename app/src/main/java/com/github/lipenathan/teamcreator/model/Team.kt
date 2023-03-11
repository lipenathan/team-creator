package com.github.lipenathan.teamcreator.model

data class Team(val players: MutableList<Player> = mutableListOf()) {

    val rating: Float get() = rating()
    val playersNumber: Int get() = players.count()

    private fun rating(): Float {
        return players.sumOf { p -> p.rate }.div(playersNumber.toFloat())
    }

    private fun hasGoalKeeper(): Boolean {
        return players.stream().anyMatch { p -> p.position == Position.GOLEIRO }
    }
}