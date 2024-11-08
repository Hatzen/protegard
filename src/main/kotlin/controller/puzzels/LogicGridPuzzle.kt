package controller.puzzels

class LogicGridPuzzle {
    private val categories = listOf("Characters", "Locations", "Items")
    private val characters = listOf("Hero", "Villain", "Sidekick", "Merchant")
    private val locations = listOf("Forest", "Castle", "Village", "Cave")
    private val items = listOf("Sword", "Potion", "Map", "Key")

    private val clues = listOf(
        "The Hero is not found in the Cave.",
        "The Villain is in the Castle.",
        "The Sidekick has the Map.",
        "The Merchant is in the Village.",
        "The Potion is found in the Forest.",
        "The Sword is with the Hero."
    )

    fun startGame() {
        println("Welcome to the Logic Grid Puzzle!")
        println("You need to deduce the relationships between the characters, locations, and items based on the following clues:")
        displayClues()

        val userAnswers = mutableMapOf<String, String>()

        while (userAnswers.size < characters.size) {
            println("\nPlease provide your answer in the format: Character - Location - Item (e.g., Hero - Castle - Sword)")
            val input = readlnOrNull()
            if (input != null) {
                val parts = input.split("-").map { it.trim() }
                if (parts.size == 3 && validateAnswer(parts)) {
                    userAnswers[parts[0]] = "${parts[1]} - ${parts[2]}"
                    println("Your answer for ${parts[0]} is recorded as ${parts[1]} - ${parts[2]}.")
                } else {
                    println("Invalid input. Please make sure to provide a valid answer.")
                }
            }
        }

        if (checkSolution(userAnswers)) {
            println("Congratulations! You solved the Logic Grid Puzzle!")
        } else {
            println("Your answers are incorrect. Please review the clues and try again.")
        }
    }

    private fun displayClues() {
        clues.forEach { println("- $it") }
    }

    private fun validateAnswer(parts: List<String>): Boolean {
        return characters.contains(parts[0]) &&
                locations.contains(parts[1]) &&
                items.contains(parts[2])
    }

    private fun checkSolution(userAnswers: Map<String, String>): Boolean {
        return userAnswers["Hero"] == "Village - Sword" &&
                userAnswers["Villain"] == "Castle - Key" &&
                userAnswers["Sidekick"] == "Forest - Map" &&
                userAnswers["Merchant"] == "Cave - Potion"
    }
}

fun main() {
    val logicGridPuzzle = LogicGridPuzzle()
    logicGridPuzzle.startGame()
}