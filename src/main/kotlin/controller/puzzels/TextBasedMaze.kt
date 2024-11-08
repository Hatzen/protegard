package controller.puzzels

import java.util.*
import kotlin.random.Random

fun main() {
    val mazeGame = TextBasedMaze()
    mazeGame.startGame()
}

// TODO: Generate random texts indicating the right way with llm. And dieing in the wrong way.

// TODO: Does not work probably..
class TextBasedMaze(private val size: Int = 5) {
    private val maze = Array(size) { Array(size) { ' ' } }
    private var start = Pair(0, 0)
    private var end = Pair(size - 1, size - 1)
    private var currentPosition = start

    init {
        generateMaze()
    }

    fun startGame() {
        println("Welcome to the Text-Based Maze!")
        println("You are at position ${currentPosition.first}, ${currentPosition.second}.")
        println("Type 'up', 'down', 'left', or 'right' to move.")
        displayMaze()

        while (currentPosition != end) {
            println("\nCurrent position: $currentPosition")
            println("Move (up, down, left, right):")
            val move = readlnOrNull()?.trim()?.lowercase(Locale.getDefault())

            if (move != null && isValidMove(move)) {
                makeMove(move)
                displayMaze()
            } else {
                println("Invalid move. Please try again.")
            }
        }

        onMazeSolved()
    }

    private fun generateMaze() {
        // Randomly set walls ('#') and empty spaces (' ') within the grid
        for (i in 0 until size) {
            for (j in 0 until size) {
                maze[i][j] = if (Random.nextDouble() > 0.7 && (i != 0 || j != 0) && (i != size - 1 || j != size - 1)) {
                    '#'
                } else {
                    ' '
                }
            }
        }

        // Randomly select a start and end position (ensuring they are not the same)
        do {
            start = Pair(Random.nextInt(size), Random.nextInt(size))
            end = Pair(Random.nextInt(size), Random.nextInt(size))
        } while (start == end) // Ensure start and end are not the same

        maze[start.first][start.second] = 'S'  // Start position
        maze[end.first][end.second] = 'E'      // End position
        currentPosition = start
    }

    private fun displayMaze() {
        println("\nMaze:")
        // Adjust for proper alignment by specifying a consistent width for each column
        for (i in maze) {
            println(i.joinToString("  ") { it.toString().padEnd(2) })  // Ensure proper spacing
        }
    }

    private fun isValidMove(move: String): Boolean {
        val (x, y) = currentPosition
        return when (move) {
            "up" -> x > 0 && maze[x - 1][y] != '#'
            "down" -> x < size - 1 && maze[x + 1][y] != '#'
            "left" -> y > 0 && maze[x][y - 1] != '#'
            "right" -> y < size - 1 && maze[x][y + 1] != '#'
            else -> false
        }
    }

    private fun makeMove(move: String) {
        val (x, y) = currentPosition
        currentPosition = when (move) {
            "up" -> Pair(x - 1, y)
            "down" -> Pair(x + 1, y)
            "left" -> Pair(x, y - 1)
            "right" -> Pair(x, y + 1)
            else -> currentPosition
        }
    }

    private fun onMazeSolved() {
        println("Congratulations! You've solved the maze!")
    }
}