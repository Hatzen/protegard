package controller.puzzels

class CryptarithmPuzzle {

    private val puzzle = mapOf(
        "ALIEN" to 0,
        "SPACE" to 1,
        "MIND" to 2,
        "TIME" to 3,
        "WIZARD" to 4
    )

    private val equation = "ALIEN + SPACE = MIND"
    private val validCharacters = puzzle.keys.flatMap { it.toList() }.distinct()

    fun startGame() {
        println("Welcome to the Cryptarithm Puzzle!")
        println("Solve the following cryptarithm:")
        println("$equation (where each letter represents a unique digit)")
        println("Enter the digits corresponding to the letters in the equation.")

        // Show the user the valid letters to substitute
        println("Valid letters: ${validCharacters.joinToString(", ")}")

        // Get user input for digits for each letter
        val userInput = mutableMapOf<Char, Int>()
        var remainingLetters = validCharacters.toMutableSet()

        while (remainingLetters.isNotEmpty()) {
            println("\nEnter the digit for letter ${remainingLetters.first()}:")
            val digitInput = readlnOrNull()?.toIntOrNull()

            if (digitInput != null && digitInput in 0..9 && !userInput.containsValue(digitInput)) {
                userInput[remainingLetters.first()] = digitInput
                remainingLetters.remove(remainingLetters.first())
            } else {
                println("Invalid input. Please enter a unique digit from 0 to 9.")
            }
        }

        // Check if the user's solution is correct
        if (checkSolution(userInput)) {
            println("Congratulations! You've solved the cryptarithm!")
        } else {
            println("Oops, that's not correct. Try again!")
        }
    }

    private fun checkSolution(userInput: Map<Char, Int>): Boolean {
        // Replace the letters with the user-provided digits
        val equationWithNumbers = equation.map { char ->
            if (char.isLetter()) {
                userInput[char]?.toString() ?: ""
            } else {
                char.toString()
            }
        }.joinToString("")

        // Solve the equation and check if the solution is correct
        val parts = equationWithNumbers.split(" ")
        val left = parts[0].toIntOrNull() ?: return false
        val right = parts[2].toIntOrNull() ?: return false

        return left + right == left // Equation: ALIEN + SPACE = MIND
    }
}

fun main() {
    val cryptarithmPuzzle = CryptarithmPuzzle()
    cryptarithmPuzzle.startGame()
}