package algorithms

import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import model.Player
import org.junit.jupiter.api.TestInstance
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SeededPlayersTest {
    private val evenPlayerList = (1..12).map { Player("Player$it", it) }.shuffled()

    @Test
    fun `test seeeded list size`() {
        val groupSize = 4
        repeat(10) { numOfGroups ->
            val numOfPlayers = numOfGroups * groupSize
            val playersList = (1..numOfPlayers).map { Player("Player$it", it) }.shuffled()
            val seededPlayers = seededPlayers(playersList, groupSize, numOfGroups)
            seededPlayers.size shouldBeExactly playersList.size
        }
    }

    @Test
    fun `test seeds order`() {
        val s = seededPlayers(evenPlayerList, 4, 3)
        s[0].seed shouldBeExactly 1
        s[4].seed shouldBeExactly 2
        s[8].seed shouldBeExactly 3
    }

    @Test
    fun `test number of seeds`() {
        getSeeds(evenPlayerList, 4).size shouldBeExactly 4
    }

    @Test fun `test remaining players list size`() {
        val seeds = evenPlayerList
            .filter { it.seed in setOf(1, 2, 3, 4) }
        val remainingPlayers = getRemainingPlayers(evenPlayerList, seeds)

        remainingPlayers.size shouldBeExactly (evenPlayerList.size - seeds.size)
    }

    @Test fun `test remaining players content`() {
        val seeds = evenPlayerList
            .filter { it.seed in setOf(1, 2, 3, 4) }
        val remainingPlayers = getRemainingPlayers(evenPlayerList, seeds)

        remainingPlayers shouldNotContain seeds
    }

    @Test
    fun `test seeds ranking`() {
        val seedRankingSet = getSeeds(evenPlayerList, 4).map { it.seed }.toSet()
        seedRankingSet shouldBe setOf(1,2,3,4)
    }
}