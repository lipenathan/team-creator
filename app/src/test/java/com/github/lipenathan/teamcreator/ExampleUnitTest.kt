package com.github.lipenathan.teamcreator

import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.model.Position
import com.github.lipenathan.teamcreator.services.persistence.PlayerMemoryDataBase
import com.github.lipenathan.teamcreator.views.GameConfigurationFragment
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val playersDb = PlayerMemoryDataBase()
    private val fragment = GameConfigurationFragment()

    @Before
    private fun before() {
        for (i in 1..10) {
            playersDb.save(
                Player(
                    "Player$i",
                    when (i) {
                        1 -> Position.GOLEIRO
                        2 -> Position.ZAGUEIRO
                        3 -> Position.LATERAL_ESQUERDO
                        4 -> Position.VOLANTE
                        5 -> Position.MEIO_CAMPO
                        6 -> Position.ATACANTE
                        7 -> Position.PONTA_DIREITA
                        8 -> Position.CENTRO_AVANTE
                        9 -> Position.LATERAL_DIREITO
                        10 -> Position.PONTA_ESQUERDA
                        else -> Position.GOLEIRO
                    },
                    when (i) {
                        1 -> Position.ZAGUEIRO
                        2 -> Position.PONTA_DIREITA
                        3 -> Position.CENTRO_AVANTE
                        4 -> Position.LATERAL_ESQUERDO
                        5 -> Position.VOLANTE
                        6 -> Position.MEIO_CAMPO
                        7 -> Position.ATACANTE
                        8 -> Position.CENTRO_AVANTE
                        9 -> Position.GOLEIRO
                        10 -> Position.ZAGUEIRO
                        else -> Position.PONTA_ESQUERDA
                    }, when (i) {
                        1 -> 1
                        2 -> 2
                        3 -> 3
                        4 -> 1
                        5 -> 2
                        6 -> 3
                        7 -> 1
                        8 -> 2
                        9 -> 3
                        10 -> 1
                        else -> 2
                    }
                )
            )
        }
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun shouldGetLowetRate() {
        val a = getLowestRate(playersDb.getAll())
        assertEquals(1, a)
    }

    fun getHighestRate(players: List<Player>): Player {
        return players.stream().min { p1, p2 -> p2.rate - p1.rate }.get()
    }

    fun getLowestRate(players: List<Player>): Player {
        return players.stream().min { p1, p2 -> p1.rate - p2.rate }.get()
    }
}