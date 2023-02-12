package model

data class Match(
    val player1: Player,
    val player2: Player,
    val winner: Player? = null
)