package controller.puzzels

import kotlin.random.Random


class MathEquationPuzzle {
    private val equations = mutableListOf<String>()
    private val variableNames = listOf("a", "b", "c", "d", "e", "f")

    init {
        generateEquations()
    }

    private fun generateEquations() {
        val numbersUsed = mutableSetOf<Int>()
        val equationsList = mutableListOf<String>()

        // Generate 3 unique equations with random numbers and operations
        for (i in 1..3) {
            val num1 = Random.nextInt(1, 10)
            val num2 = Random.nextInt(1, 10)
            val num3 = Random.nextInt(1, 10)
            numbersUsed.add(num1)
            numbersUsed.add(num2)
            numbersUsed.add(num3)

            val operation = when (Random.nextInt(1, 4)) {
                1 -> "+"
                2 -> "-"
                3 -> "*"
                else -> "/"
            }

            val equation = if (i == 1) {
                "$num1 $operation $num2 = ?"
            } else {
                // Include a number from the previous equations
                val prevNum = if (i == 2) num1 else num2
                "$prevNum $operation $num3 = ?"
            }

            equationsList.add(equation)
        }

        // Replace numbers with variables
        val variableMap = mutableMapOf<Int, String>()
        var index = 0
        for (number in numbersUsed) {
            variableMap[number] = variableNames[index++]
        }

        for (equation in equationsList) {
            var modifiedEquation = equation
            for ((number, variable) in variableMap) {
                modifiedEquation = modifiedEquation.replace(number.toString(), variable)
            }
            equations.add(modifiedEquation)
        }
    }

    fun startGame(onCorrectAnswer: (Map<String, Int>) -> Unit) {
        println("Solve the following equations:")
        equations.forEach { println(it) }

        var isCorrect = false
        while (!isCorrect) {
            val userValues = mutableMapOf<String, Int>()

            println("Enter values for variables ${variableNames.joinToString(", ")}:")
            for (variable in variableNames) {
                print("$variable: ")
                userValues[variable] = readlnOrNull()?.toIntOrNull() ?: continue
            }

            // Validate the user's solutions
            isCorrect = equations.all { equation ->
                val results = equation.split(" = ?")[0].trim().split(" ")
                val leftValue = calculateValue(results, userValues)

                // Check if the left value equals the right side (which is always 0 here)
                leftValue == 0
            }

            if (isCorrect) {
                println("Correct! You solved the equations.")
                onCorrectAnswer(userValues)
            } else {
                println("Incorrect values, please try again.")
            }
        }
    }

    private fun calculateValue(results: List<String>, userValues: Map<String, Int>): Int {
        var total = 0
        var currentOperation: String? = null

        for (token in results) {
            when (token) {
                "+" -> currentOperation = "+"
                "-" -> currentOperation = "-"
                "*" -> currentOperation = "*"
                "/" -> currentOperation = "/"
                else -> {
                    val value = userValues[token] ?: return 0
                    total = when (currentOperation) {
                        "+" -> total + value
                        "-" -> total - value
                        "*" -> if (total == 0) value else total * value
                        "/" -> if (total == 0) value else total / value
                        else -> value
                    }
                }
            }
        }
        return total
    }
}

fun main() {
    val game = MathEquationPuzzle()
    game.startGame { correctValues ->
        println("Congratulations! You solved the equations correctly with values: $correctValues")
    }
}