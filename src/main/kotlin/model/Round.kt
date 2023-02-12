package model

data class Round(
    val id: Int,
    val matches: List<Match>
)

fun Round.printMatches() {
    println("Confrontos - Round $id")
    matches.forEach { match ->
        println("${match.player1.name} X ${match.player2.name}")
    }
    println()
}