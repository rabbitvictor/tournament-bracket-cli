package algorithms

import model.Group
import model.Player
import model.Round
import model.printMatches

fun createGroups(players: List<Player>, groupSize: Int): List<Group> {
    return players
        .chunked(groupSize)
        .mapIndexed { i, p -> Group(i, p, roundRobin(p)) }
}


fun main() {
    val groupSize = 4
    val players = (1..32)
        .map { Player("Player$it", it) }
        .shuffled()

    val groups = createGroups(players, groupSize)
    groups.forEach {
        println("Grupo ${it.id}")
        it.groupRounds.forEach(Round::printMatches)
    }

    println("break")
}