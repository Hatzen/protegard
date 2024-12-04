package de.hartz.software.protegard.controller.puzzels.unused.erroneous

import java.util.*

// TODO: Use more for a pin code or color code so users are able to guess.. And fix at all..
class MastermindGame {
    private val categories = mapOf(
        "electricity" to listOf("voltage", "current", "resistance", "circuit"),
        "1920" to listOf("jazz", "flapper", "prohibition", "suffrage"),
        "numbers" to listOf("one", "two", "three", "four"),
        "religion" to listOf("buddhism", "christianity", "hinduism", "islam"),
        "astrology" to listOf("aries", "taurus", "gemini", "cancer"),
        "astronomy" to listOf("planet", "star", "galaxy", "comet")
    )

    private lateinit var secretSequence: List<String>
    private val codeLength = 4
    private val maxAttempts = 10

    fun startGame() {
        println("Welcome to Mastermind!")
        selectSecretSequence()

        for (attempt in 1..maxAttempts) {
            println("\nAttempt $attempt of $maxAttempts. Enter your guess (comma-separated):")
            val userInput = readlnOrNull()
            if (userInput != null) {
                val guess = userInput.split(",").map { it.trim().lowercase(Locale.getDefault()) }

                val feedback = provideFeedback(guess)
                println("Feedback: Correct Position: ${feedback.first}, Correct Color: ${feedback.second}")

                if (feedback.first == codeLength) {
                    println("Congratulations! You've guessed the correct sequence: ${secretSequence.joinToString(", ")}!")
                    return
                }
            }
        }
        println("You've used all attempts. The correct sequence was: ${secretSequence.joinToString(", ")}.")
    }

    private fun selectSecretSequence() {
        val category = categories.keys.random()
        val elements = categories[category] ?: emptyList()
        secretSequence = List(codeLength) { elements.random() }
        println("A secret sequence has been selected from the category: $category.")
    }

    private fun provideFeedback(guess: List<String>): Pair<Int, Int> {
        if (guess.size != codeLength) {
            println("Your guess must contain exactly $codeLength elements.")
            return 0 to 0
        }

        var correctPosition = 0
        var correctColor = 0
        val checkedIndices = mutableSetOf<Int>()

        // Check for correct positions
        for (i in guess.indices) {
            if (guess[i] == secretSequence[i]) {
                correctPosition++
                checkedIndices.add(i) // Mark this index as checked
            }
        }

        // Check for correct colors (but wrong positions)
        for (i in guess.indices) {
            if (i !in checkedIndices && guess[i] in secretSequence) {
                correctColor++
                // Find the first occurrence of this color in the secret sequence that hasn't been matched yet
                val indexInSecret = secretSequence.indexOf(guess[i])
                if (indexInSecret >= 0) {
                    checkedIndices.add(indexInSecret) // Mark this index as checked
                }
            }
        }

        return correctPosition to correctColor
    }
}

fun main() {
    val mastermindGame = MastermindGame()
    mastermindGame.startGame()
}