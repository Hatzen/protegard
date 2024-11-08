package controller.puzzels

class RiverCrossingPuzzle {
    private val leftBank = mutableListOf("Friendly Alien", "Evil Alien", "Human", "Disease")
    private val rightBank = mutableListOf<String>()

    fun startGame() {
        println("Welcome to the River Crossing Puzzle!")
        println("You need to get all entities across the river without leaving the Human alone with the Evil Alien or the Disease.")

        while (true) {
            printStatus()
            if (isSolved()) {
                println("Congratulations! You have successfully crossed the river!")
                break
            }
            println("What will you take across the river? (Friendly Alien, Evil Alien, Human, Disease, or None to cross empty)")
            val input = readlnOrNull()?.trim()
            if (input != null) {
                makeMove(input)
            } else {
                println("Invalid input. Please try again.")
            }
        }
    }

    private fun printStatus() {
        println("\nCurrent status:")
        println("Left Bank: ${leftBank.joinToString(", ")}")
        println("Right Bank: ${rightBank.joinToString(", ")}")
    }

    private fun makeMove(entity: String) {
        if (entity !in leftBank && entity != "None") {
            println("You can't take that entity across. It’s not on the left bank.")
            return
        }
        if (entity == "None") {
            crossRiver(emptyList())
        } else {
            crossRiver(listOf(entity))
        }
    }

    private fun crossRiver(entities: List<String>) {
        // Move entities from left bank to right bank
        for (entity in entities) {
            leftBank.remove(entity)
            rightBank.add(entity)
        }

        // Check for illegal states after crossing
        if (isIllegalState()) {
            // Undo move
            for (entity in entities) {
                rightBank.remove(entity)
                leftBank.add(entity)
            }
            println("You can't leave the Human alone with the Evil Alien or the Disease! Try again.")
        } else {
            println("Crossed river with: ${entities.joinToString(", ") { it }}")
            // Return alone (the boat can only go back with one entity)
            if (leftBank.isNotEmpty()) {
                returnToLeftBank()
            }
        }
    }

    private fun returnToLeftBank() {
        // The boat returns empty to the left bank
        println("The boat returns to the left bank.")
    }

    private fun isIllegalState(): Boolean {
        // Check for illegal states
        if (leftBank.contains("Human") && leftBank.contains("Evil Alien") && leftBank.contains("Disease")) {
            return true // Human alone with Evil Alien and Disease
        }
        if (rightBank.contains("Human") && rightBank.contains("Evil Alien") && rightBank.contains("Disease")) {
            return true // Human alone with Evil Alien and Disease
        }
        return false
    }

    private fun isSolved(): Boolean {
        // Check if all entities are on the right bank
        return leftBank.isEmpty() && rightBank.size == 4
    }
}

fun main() {
    val game = RiverCrossingPuzzle()
    game.startGame()
}