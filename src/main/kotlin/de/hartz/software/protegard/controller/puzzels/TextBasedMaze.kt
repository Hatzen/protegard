package de.hartz.software.protegard.controller.puzzels

import de.hartz.software.protegard.controller.IView
import de.hartz.software.protegard.controller.generative.translation.TranslationGPT
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.settings.Settings
import de.hartz.software.protegard.view.text.TextIO
import kotlin.random.Random

fun main() {
    val mazeGame = TextBasedMaze(TextIO(TranslationGPT(Settings)))
    mazeGame.startGame()
}

// TODO: Generate random texts indicating the right way with llm. And dieing in the wrong way.
class TextBasedMaze(private val view: IView, private val size: Int = 5) {
    private val maze = Array(size) { Array(size) { ' ' } }
    private var start = Pair(0, 0)
    private var end = Pair(size - 1, size - 1)
    private var currentPosition = start

    init {
        generateMaze()
    }

    fun startGame() {
        view.addText("You are at position ${currentPosition.first}, ${currentPosition.second}.", Characters.NARRATOR)
        view.addText("Type 1. up, 2. down, 3. left, or 4. right to move.")
        displayMaze()

        while (currentPosition != end) {
            view.addText("\nCurrent position: $currentPosition")
            val move = view.getChoice()

            if (isValidMove(move)) {
                makeMove(move)
                displayMaze()
            } else {
                view.addText("Invalid move. Please try again.")
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

        view.addText("S $start , E $end")

        currentPosition = start
    }

    private fun displayMaze() {
        // Adjust for proper alignment by specifying a consistent width for each column
        for (i in maze) {
            view.addText(i.joinToString("  ") { it.toString().padEnd(2) })  // Ensure proper spacing
        }
    }

    private fun isValidMove(move: Int): Boolean {
        val (x, y) = currentPosition
        return when (move) {
            1 -> x > 0 && maze[x - 1][y] != '#'           // "up"
            2 -> x < size - 1 && maze[x + 1][y] != '#'    // "down"
            3 -> y > 0 && maze[x][y - 1] != '#'           // "left"
            4 -> y < size - 1 && maze[x][y + 1] != '#'    // "right"
            else -> false
        }
    }

    private fun makeMove(move: Int) {
        val (x, y) = currentPosition
        currentPosition = when (move) {
            1 -> Pair(x - 1, y)    // "up"
            2 -> Pair(x + 1, y)  // "down
            3 -> Pair(x, y - 1)  // "left
            4 -> Pair(x, y + 1) // "righ
            else -> currentPosition
        }
    }

    private fun onMazeSolved() {

    }
}