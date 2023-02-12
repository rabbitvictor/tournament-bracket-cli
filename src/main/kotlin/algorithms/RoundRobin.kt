package algorithms

import model.Group
import model.Match
import model.Player
import model.Round
import model.printMatches

private fun rotatePlayers(p: MutableList<Player>): MutableList<Player> {
    require(p.size % 2 == 0) { "list size needs to be even!" }

    val first = p[0]
    val second = p[1]
    val remainingList = p.takeLast(p.size - 2).toTypedArray()
    return mutableListOf(first, *remainingList, second)
}

fun roundRobin(p: List<Player>): List<Round> {
    var players = p.sortedBy { it.seed }.toMutableList()
    if (players.size % 2 == 1) players.add(Player("bye", -1))
    val numOfMatches = players.size / 2
    val rounds = mutableListOf<Round>()

    repeat(players.size - 1) { roundNumber ->
        val firstHalf = players.take(numOfMatches)
        val secondHalf = players.takeLast(numOfMatches)
        val matches = firstHalf
            .zip(secondHalf)
            .map { Match(it.first, it.second) }
        rounds += Round(roundNumber + 1, matches)
        players = rotatePlayers(players)
    }

    return rounds
}

fun main() {
    val groupId = 1
    val players = listOf(
        Player("Victor", 1),
        Player("Guilherme", 2),
        Player("Matheus", 3),
        Player("Talles", 4),
    ).sortedBy { it.seed }
    val groupRounds = roundRobin(players)
    val group = Group(groupId, players, groupRounds)
    group.groupRounds.forEach(Round::printMatches)

    println("breakpoint")
}
