package algorithms

import model.Player
import kotlin.math.ceil


fun seededPlayers(unseededPlayers: List<Player>, groupSize: Int, numOfGroups: Int): List<Player> {
    require(unseededPlayers.size <= groupSize * numOfGroups) {
        "Insufficient number of players! At least ${groupSize * numOfGroups} players are needed!"
    }

    require(unseededPlayers.size >= numOfGroups * 2) {
        "Insufficient number of players! At least ${numOfGroups * 2} players are needed!"
    }

    val seeds = getSeeds(unseededPlayers, numOfGroups).sortedBy { it.seed }.toMutableList()
    val remainingPlayers = getRemainingPlayers(unseededPlayers, seeds).shuffled()
    val seededPlayers = mutableListOf<Player>()

    repeat(numOfGroups) {
        seededPlayers += seeds.removeFirst()
        seededPlayers += selectCurrentGroupPlayers(remainingPlayers, seededPlayers, groupSize)
    }

    return seededPlayers
}

private fun selectCurrentGroupPlayers(
    remainingPlayers: List<Player>,
    seededPlayers: MutableList<Player>,
    groupSize: Int
): List<Player> {
    val availablePlayers = remainingPlayers
        .filterNot {
            it in seededPlayers
        }

    return if (availablePlayers.size >= groupSize - 1) {
        remainingPlayers.take(groupSize - 1)
    } else {
        remainingPlayers
    }
}

internal fun getSeeds(
    unseededPlayers: List<Player>,
    numOfGroups: Int
) = unseededPlayers.sortedBy { it.seed }.take(numOfGroups)

internal fun getRemainingPlayers(
    unseededPlayers: List<Player>,
    seeds: List<Player>
) = unseededPlayers.filter { it !in seeds }

private fun roundIntUp(x: Int, y: Int) = ceil((x / y).toDouble()).toInt()