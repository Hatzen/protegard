package org.example.controller.puzzels

import java.util.*
import kotlin.random.Random

// TODO: Build proper api, make AI working.
abstract class ChessPiece(val color: String) {
    abstract fun symbol(): String
    abstract fun validMoves(board: ChessBoard, position: Pair<Int, Int>): List<Pair<Int, Int>>
}

class Pawn(color: String) : ChessPiece(color) {
    override fun symbol(): String = if (color == "white") "P" else "p"

    override fun validMoves(board: ChessBoard, position: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = position
        val direction = if (color == "white") -1 else 1
        val validMoves = mutableListOf<Pair<Int, Int>>()

        // Move forward
        if (board.isWithinBounds(row + direction, col) && board.getPiece(row + direction, col) == null) {
            validMoves.add(Pair(row + direction, col))
        }

        // Capture diagonally
        if (board.isWithinBounds(row + direction, col - 1) && board.getPiece(
                row + direction,
                col - 1
            )?.color != color
        ) {
            validMoves.add(Pair(row + direction, col - 1))
        }
        if (board.isWithinBounds(row + direction, col + 1) && board.getPiece(
                row + direction,
                col + 1
            )?.color != color
        ) {
            validMoves.add(Pair(row + direction, col + 1))
        }

        return validMoves
    }
}

class Rook(color: String) : ChessPiece(color) {
    override fun symbol(): String = if (color == "white") "R" else "r"

    override fun validMoves(board: ChessBoard, position: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = position
        val validMoves = mutableListOf<Pair<Int, Int>>()

        // Horizontal and vertical moves
        for (i in 0 until 8) {
            if (i != row) validMoves.add(Pair(i, col)) // Vertical
            if (i != col) validMoves.add(Pair(row, i)) // Horizontal
        }

        return validMoves.filter {
            board.isPathClear(position, it) && (board.getPiece(
                it.first,
                it.second
            ) == null || board.getPiece(it.first, it.second)!!.color != color)
        }
    }
}

class Knight(color: String) : ChessPiece(color) {
    override fun symbol(): String = if (color == "white") "N" else "n"

    override fun validMoves(board: ChessBoard, position: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = position
        val moves = listOf(
            Pair(row - 2, col - 1), Pair(row - 2, col + 1),
            Pair(row + 2, col - 1), Pair(row + 2, col + 1),
            Pair(row - 1, col - 2), Pair(row - 1, col + 2),
            Pair(row + 1, col - 2), Pair(row + 1, col + 2)
        )

        return moves.filter {
            board.isWithinBounds(it.first, it.second) && (board.getPiece(
                it.first,
                it.second
            ) == null || board.getPiece(it.first, it.second)!!.color != color)
        }
    }
}

class Bishop(color: String) : ChessPiece(color) {
    override fun symbol(): String = if (color == "white") "B" else "b"

    override fun validMoves(board: ChessBoard, position: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = position
        val validMoves = mutableListOf<Pair<Int, Int>>()

        // Diagonal moves
        for (i in 1 until 8) {
            validMoves.add(Pair(row + i, col + i))   // Down right
            validMoves.add(Pair(row + i, col - i))   // Down left
            validMoves.add(Pair(row - i, col + i))   // Up right
            validMoves.add(Pair(row - i, col - i))   // Up left
        }

        return validMoves.filter {
            board.isPathClear(position, it) && (board.getPiece(
                it.first,
                it.second
            ) == null || board.getPiece(it.first, it.second)!!.color != color)
        }
    }
}

class Queen(color: String) : ChessPiece(color) {
    override fun symbol(): String = if (color == "white") "Q" else "q"

    override fun validMoves(board: ChessBoard, position: Pair<Int, Int>): List<Pair<Int, Int>> {
        val rookMoves = Rook(color).validMoves(board, position)
        val bishopMoves = Bishop(color).validMoves(board, position)
        return (rookMoves + bishopMoves).distinct()
    }
}

class King(color: String) : ChessPiece(color) {
    override fun symbol(): String = if (color == "white") "K" else "k"

    override fun validMoves(board: ChessBoard, position: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = position
        val moves = listOf(
            Pair(row - 1, col - 1), Pair(row - 1, col), Pair(row - 1, col + 1),
            Pair(row, col - 1), Pair(row, col + 1),
            Pair(row + 1, col - 1), Pair(row + 1, col), Pair(row + 1, col + 1)
        )

        return moves.filter {
            board.isWithinBounds(it.first, it.second) && (board.getPiece(
                it.first,
                it.second
            ) == null || board.getPiece(it.first, it.second)!!.color != color)
        }
    }
}

class ChessBoard {
    private val board: Array<Array<ChessPiece?>> = Array(8) { arrayOfNulls<ChessPiece>(8) }
    var currentTurn: String = "white"

    init {
        setupBoard()
    }

    private fun setupBoard() {
        // Set up pieces
        for (i in 0 until 8) {
            board[1][i] = Pawn("white")
            board[6][i] = Pawn("black")
        }

        board[0][0] = Rook("white")
        board[0][1] = Knight("white")
        board[0][2] = Bishop("white")
        board[0][3] = Queen("white")
        board[0][4] = King("white")
        board[0][5] = Bishop("white")
        board[0][6] = Knight("white")
        board[0][7] = Rook("white")

        board[7][0] = Rook("black")
        board[7][1] = Knight("black")
        board[7][2] = Bishop("black")
        board[7][3] = Queen("black")
        board[7][4] = King("black")
        board[7][5] = Bishop("black")
        board[7][6] = Knight("black")
        board[7][7] = Rook("black")
    }

    fun printBoard() {
        println("  a b c d e f g h")
        for (i in 0 until 8) {
            print(8 - i)
            for (j in 0 until 8) {
                val piece = board[i][j]
                print(" ${piece?.symbol() ?: '.'}")
            }
            println(" ${8 - i}")
        }
        println("  a b c d e f g h")
    }

    fun movePiece(start: String, end: String) {
        val (startRow, startCol) = getCoordinates(start)
        val (endRow, endCol) = getCoordinates(end)

        val piece = board[startRow][startCol]
        if (piece != null && piece.color == currentTurn) {
            val validMoves = piece.validMoves(this, Pair(startRow, startCol))
            if (Pair(endRow, endCol) in validMoves) {
                board[endRow][endCol] = piece
                board[startRow][startCol] = null
                currentTurn = if (currentTurn == "white") "black" else "white"
            } else {
                println("Invalid move.")
            }
        } else {
            println("Invalid move.")
        }
    }

    private fun getCoordinates(pos: String): Pair<Int, Int> {
        val col = pos[0] - 'a'
        val row = 8 - (pos[1] - '0')
        return Pair(row, col)
    }

    fun getPiece(row: Int, col: Int): ChessPiece? {
        return if (isWithinBounds(row, col)) board[row][col] else null
    }

    fun isWithinBounds(row: Int, col: Int): Boolean {
        return row in 0 until 8 && col in 0 until 8
    }

    fun isPathClear(start: Pair<Int, Int>, end: Pair<Int, Int>): Boolean {
        val (startRow, startCol) = start
        val (endRow, endCol) = end
        if (startRow == endRow) {
            val range = if (startCol < endCol) startCol + 1 until endCol else endCol + 1 until startCol
            return range.all { board[startRow][it] == null }
        } else if (startCol == endCol) {
            val range = if (startRow < endRow) startRow + 1 until endRow else endRow + 1 until startRow
            return range.all { board[it][startCol] == null }
        } else if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) {
            val rowDirection = if (endRow > startRow) 1 else -1
            val colDirection = if (endCol > startCol) 1 else -1
            var currentRow = startRow + rowDirection
            var currentCol = startCol + colDirection
            while (currentRow != endRow && currentCol != endCol) {
                if (board[currentRow][currentCol] != null) return false
                currentRow += rowDirection
                currentCol += colDirection
            }
            return true
        }
        return false
    }
}

class SimpleAI(private val board: ChessBoard, private val color: String) {
    fun makeMove() {
        val validMoves = mutableListOf<Pair<String, String>>()
        for (row in 0 until 8) {
            for (col in 0 until 8) {
                val piece = board.getPiece(row, col)
                if (piece?.color == color) {
                    val moves = piece.validMoves(board, Pair(row, col))
                    for (move in moves) {
                        validMoves.add(Pair("${rowToChar(row)}${8 - row}", "${rowToChar(move.first)}${8 - move.first}"))
                    }
                }
            }
        }
        if (validMoves.isNotEmpty()) {
            val move = validMoves[Random.nextInt(validMoves.size)]
            println("AI moves: ${move.first} ${move.second}")
            board.movePiece(move.first, move.second)
        }
    }

    private fun rowToChar(row: Int): Char = ('a' + row)
}

fun main() {
    val chessGame = ChessBoard()
    val ai = SimpleAI(chessGame, "black")

    while (true) {
        chessGame.printBoard()
        println("${chessGame.currentTurn.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}'s turn. Enter your move (e.g., e2 e4): ")
        val move = readlnOrNull() ?: break
        if (move.lowercase() == "quit") break

        try {
            val (start, end) = move.split(" ")
            chessGame.movePiece(start, end)
            ai.makeMove()
        } catch (e: Exception) {
            println("Invalid input. Please enter in the format 'e2 e4'.")
        }
    }
}
