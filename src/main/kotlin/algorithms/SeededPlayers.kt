package algorithms

import model.Player
import kotlin.math.ceil


// TODO: DOESNT WORK!
fun seededPlayers(unseededPlayers: List<Player>, groupSize: Int): List<Player> {
    require(unseededPlayers.size >= groupSize) {
        "Insufficient number of players! At least $groupSize players are needed!"
    }

    val numOfGroups = roundIntUp(unseededPlayers.size, groupSize)
    val seeds = unseededPlayers.sortedBy { it.seed }.take(numOfGroups).toMutableList()
    val seededPlayers = unseededPlayers
        .shuffled()
        .toMutableList()
        .apply { seeds.forEach(::remove) }
        .chunked(groupSize - 1)
        .flatMap {
            val seed = listOf(seeds.removeFirst())
            seed + it
        }

    return seededPlayers
}

private fun roundIntUp(x: Int, y: Int) = ceil((x / y).toDouble()).toInt()

fun main() {
    val players = (1..11).map {
        Player("Player$it", it)
    }
    val seededPlayers = seededPlayers(players, 4)
    println("breakpoint")
}