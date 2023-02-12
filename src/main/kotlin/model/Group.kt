package model

data class Group(
    val id: Int,
    val players: List<Player> = emptyList(),
    val groupRounds: List<Round> = emptyList(),
)

