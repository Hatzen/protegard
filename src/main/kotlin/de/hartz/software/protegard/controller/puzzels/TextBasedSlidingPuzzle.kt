package de.hartz.software.protegard.controller.puzzels


fun main() {
    println("Enter the side length of the sliding puzzle:")
    val sideLength = readlnOrNull()?.toIntOrNull() ?: return
    val puzzle = TextBasedSlidingPuzzle(sideLength)
    puzzle.play()
}

class TextBasedSlidingPuzzle(private val size: Int) {
    private val board: Array<Array<Int>>
    private val emptySlot = size * size
    private var emptyRow: Int = size - 1
    private var emptyCol: Int = size - 1

    init {
        board = generateBoard()
        randomizeBoard()
    }

    private fun generateBoard(): Array<Array<Int>> {
        var num = 1
        return Array(size) { row ->
            Array(size) { col ->
                if (row == size - 1 && col == size - 1) emptySlot else num++
            }
        }
    }

    private fun randomizeBoard() {
        repeat(size * size * 10) {
            val adjacent = getMovableNumbers()
            val randomChoice = adjacent.random()
            move(randomChoice)
        }
    }

    fun play() {
        while (true) {
            printBoard()
            if (isSolved()) {
                println("Congratulations! You solved the puzzle!")
                break
            }
            println("Enter a number to slide into the empty slot:")
            val number = readlnOrNull()?.toIntOrNull() ?: continue
            if (!move(number)) {
                println("Invalid move. Try again.")
            }
        }
    }

    private fun printBoard() {
        board.forEach { row ->
            println(row.joinToString(" ") { if (it == emptySlot) " " else it.toString().padStart(2, ' ') })
        }
        println()
    }

    private fun getMovableNumbers(): List<Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        return directions.mapNotNull { (dr, dc) ->
            val newRow = emptyRow + dr
            val newCol = emptyCol + dc
            if (newRow in 0 until size && newCol in 0 until size) board[newRow][newCol] else null
        }
    }

    private fun move(number: Int): Boolean {
        for (r in 0 until size) {
            for (c in 0 until size) {
                if (board[r][c] == number && isAdjacentToEmpty(r, c)) {
                    board[emptyRow][emptyCol] = number
                    board[r][c] = emptySlot
                    emptyRow = r
                    emptyCol = c
                    return true
                }
            }
        }
        return false
    }

    private fun isAdjacentToEmpty(r: Int, c: Int): Boolean {
        return (r == emptyRow && (c == emptyCol - 1 || c == emptyCol + 1)) ||
                (c == emptyCol && (r == emptyRow - 1 || r == emptyRow + 1))
    }

    private fun isSolved(): Boolean {
        var num = 1
        for (r in 0 until size) {
            for (c in 0 until size) {
                if (r == size - 1 && c == size - 1) return true
                if (board[r][c] != num++) return false
            }
        }
        return true
    }
}