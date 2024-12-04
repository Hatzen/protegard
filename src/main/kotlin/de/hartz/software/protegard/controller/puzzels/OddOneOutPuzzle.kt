package de.hartz.software.protegard.controller.puzzels

import kotlin.random.Random

class OddOneOutPuzzle {
    private val categories = mapOf(
        "Vikings" to listOf("Thor", "Odin", "Loki", "Freyja", "Ragnar"),
        "Science" to listOf("Newton", "Galileo", "Kepler", "Darwin", "Lavoisier"),
        "Aliens" to listOf("Martian", "Visitor", "Meteor", "Star", "Planet"),
        "Sky" to listOf("Cloud", "Rain", "Storm", "Hail", "Snow"),
        "Chemistry" to listOf("Hydrogen", "Oxygen", "Nitrogen", "Sulfur", "Iron"),
        "Philosophy" to listOf("Plato", "Socrates", "Aristotle", "Descartes", "Kant"),
        "Physics" to listOf("Ether", "Light", "Heat", "Gravity", "Magnetism"),
        "Society" to listOf("Monarchy", "Republic", "Empire", "Feudalism", "Anarchy"),
        "Informatics" to listOf("Abacus", "Algorithm", "Cipher", "Telegraph", "Calculator")
    )

    fun startGame(onCorrectAnswer: (setNumber: Int, elements: List<String>, oddOneOut: String) -> Unit) {
        repeat(10) { setNumber ->
            val category = categories.entries.random()
            val elements = category.value.toMutableList()

            // Ensure the odd element is not part of the category
            var oddOneOut: String
            do {
                oddOneOut = categories.entries.random().value.random()
                elements.shuffle()
            } while (elements.contains(oddOneOut))

            // Insert the odd element at a random position
            elements.add(Random.nextInt(elements.size), oddOneOut)

            println("Set ${setNumber + 1}: Identify the odd one out.")
            println(elements.joinToString(", "))

            var attempts = 0
            while (true) {
                print("Enter the odd one out: ")
                val userAnswer = readlnOrNull()?.trim()
                attempts++

                if (userAnswer.equals(oddOneOut, ignoreCase = true)) {
                    println("Correct! You solved it in $attempts attempts.")
                    onCorrectAnswer(setNumber + 1, elements, oddOneOut)
                    break
                } else {
                    println("Incorrect, try again.")
                }
            }
        }
    }
}

fun main() {
    val game = OddOneOutPuzzle()
    game.startGame { setNumber, elements, oddOneOut ->
        println("Congratulations! You found the odd one out in set $setNumber.")
        println("Set: ${elements.joinToString(", ")}")
        println("The odd one out was: $oddOneOut")
    }
}