package controller.puzzels

import kotlin.random.Random

class TowerOfHanoi {
    private val towers = Array(4) { mutableListOf<Int>() } // 4 towers
    private val numberOfDisks = 4 // Number of disks
    private var moves = 0

    init {
        initializeGame()
    }

    fun startGame() {
        println("Welcome to the Tower of Hanoi with 4 Towers!")
        displayTowers()

        while (!isSolved()) {
            println("Enter your move in the format: fromTower toTower (e.g., 1 2 to move from Tower 1 to Tower 2):")
            val input = readlnOrNull()
            if (input != null) {
                val move = input.split(" ")
                if (move.size == 2 && move.all { it.toIntOrNull() in 1..4 }) {
                    val from = move[0].toInt() - 1
                    val to = move[1].toInt() - 1
                    if (makeMove(from, to)) {
                        moves++
                        displayTowers()
                    } else {
                        println("Invalid move. Please try again.")
                    }
                } else {
                    println("Invalid input format. Please enter two tower numbers.")
                }
            }
        }
        println("Congratulations! You solved the Tower of Hanoi in $moves moves!")
    }

    private fun initializeGame() {
        // Randomly distribute disks among the first three towers
        val disks = (1..numberOfDisks).toList()
        disks.shuffled().forEach { disk ->
            towers[Random.nextInt(3)].add(disk)
        }
        towers.forEach { it.sort() } // Ensure that each tower has its disks sorted for display
    }

    private fun makeMove(from: Int, to: Int): Boolean {
        if (towers[from].isNotEmpty() && (towers[to].isEmpty() || towers[to].last() > towers[from].last())) {
            towers[to].add(towers[from].removeAt(towers[from].lastIndex))
            return true
        }
        return false
    }

    private fun isSolved(): Boolean {
        // Check if all disks are moved to the last tower
        return towers[3].size == numberOfDisks
    }

    private fun displayTowers() {
        println("\nCurrent state of towers:")
        towers.forEachIndexed { index, tower ->
            println("Tower ${index + 1}: ${tower.joinToString(", ") { "Disk $it" }}")
        }
    }
}

fun main() {
    val hanoiGame = TowerOfHanoi()
    hanoiGame.startGame()
}