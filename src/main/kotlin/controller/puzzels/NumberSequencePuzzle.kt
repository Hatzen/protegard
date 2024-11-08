package controller.puzzels

import kotlin.math.pow
import kotlin.random.Random

class NumberSequencePuzzle {
    private val sequencePatterns = listOf(
        ::generateArithmeticSequence,
        ::generateGeometricSequence,
        ::generateFibonacciSequence,
        ::generateSquareNumberSequence,
        ::generateCubicNumberSequence
    )

    fun startGame(onCorrectAnswer: (sequence: List<Int>, missingIndex: Int, correctAnswer: Int) -> Unit) {
        val pattern = sequencePatterns.random()
        val sequence = pattern.invoke(Random.nextInt(5, 10))
        val missingIndex = Random.nextInt(1, sequence.size - 1) // Avoid first and last
        val correctAnswer = sequence[missingIndex]
        val sequenceWithMissing = sequence.toMutableList().apply { this[missingIndex] = -1 }

        println("Identify the missing number in the sequence:")
        println(sequenceWithMissing.joinToString(", ") { if (it == -1) "?" else it.toString() })

        var attempts = 0
        while (true) {
            print("Enter the missing number: ")
            val userAnswer = readlnOrNull()?.toIntOrNull()
            attempts++

            if (userAnswer == correctAnswer) {
                println("Correct! You solved it in $attempts attempts.")
                onCorrectAnswer(sequence, missingIndex, correctAnswer)
                break
            } else {
                if (attempts < 3) {
                    println("Incorrect, try again.")
                } else {
                    println("Incorrect. Hint: The difference between consecutive terms may help.")
                }
            }
        }
    }

    private fun generateArithmeticSequence(length: Int): List<Int> {
        val start = Random.nextInt(1, 10)
        val step = Random.nextInt(1, 5)
        return List(length) { start + it * step }
    }

    private fun generateGeometricSequence(length: Int): List<Int> {
        val start = Random.nextInt(1, 5)
        val ratio = Random.nextInt(2, 5)
        return List(length) { start * ratio.toDouble().pow(it).toInt() }
    }

    private fun generateFibonacciSequence(length: Int): List<Int> {
        val sequence = mutableListOf(0, 1)
        while (sequence.size < length) {
            sequence.add(sequence[sequence.size - 1] + sequence[sequence.size - 2])
        }
        return sequence
    }

    private fun generateSquareNumberSequence(length: Int): List<Int> {
        return List(length) { (it + 1).toDouble().pow(2).toInt() }
    }

    private fun generateCubicNumberSequence(length: Int): List<Int> {
        return List(length) { (it + 1).toDouble().pow(3).toInt() }
    }
}

fun main() {
    val game = NumberSequencePuzzle()
    game.startGame { sequence, missingIndex, correctAnswer ->
        println("Congratulations! You correctly identified the missing number.")
        println("Sequence: ${sequence.joinToString(", ")}")
        println("The missing number at index $missingIndex was $correctAnswer.")
    }
}
