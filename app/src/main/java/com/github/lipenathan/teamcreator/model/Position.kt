package com.github.lipenathan.teamcreator.model

enum class Position() {
    GOLEIRO("Goleiro"), ZAGUEIRO("Zagueiro"), LATERAL_ESQUERDO("Lateral esquerdo"), LATERAL_DIREITO("Lateral direito"),
    MEIO_CAMPO("Meio campo"), VOLANTE("Volante"), PONTA_ESQUERDA("Ponta esquerda"), PONTA_DIREITA("Ponta direita"),
    ATACANTE("Atacante"), CENTRO_AVANTE("Centro avante");

    var flag: String = ""

    constructor(flag: String) : this() {
        this.flag = flag
    }

    companion object {
        fun getPositionByName(name: String): Position {
            return when (name) {
                GOLEIRO.flag -> GOLEIRO
                ZAGUEIRO.flag -> ZAGUEIRO
                LATERAL_ESQUERDO.flag -> LATERAL_ESQUERDO
                LATERAL_DIREITO.flag -> LATERAL_DIREITO
                MEIO_CAMPO.flag -> MEIO_CAMPO
                VOLANTE.flag -> VOLANTE
                PONTA_ESQUERDA.flag -> PONTA_ESQUERDA
                PONTA_DIREITA.flag -> PONTA_DIREITA
                ATACANTE.flag -> ATACANTE
                CENTRO_AVANTE.flag -> CENTRO_AVANTE
                else -> MEIO_CAMPO
            }
        }

        fun getAllFlags(): List<String> {
            return Position.values().map { it.flag }
        }
    }
}