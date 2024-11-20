package de.hartz.software.protegard.controller.puzzels

import java.util.*

abstract class Piece(val color: String) {
    abstract val symbol: Char
    abstract fun validMoves(board: ChessBoard, position: Pair<Int, Int>): List<Pair<Int, Int>>
}

class Pawn(color: String) : Piece(color) {
    override val symbol = if (color == "white") 'P' else 'p'
    override fun validMoves(board: ChessBoard, pos: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = pos
        val direction = if (color == "white") -1 else 1
        val moves = mutableListOf<Pair<Int, Int>>()
        if (board.isEmpty(row + direction, col)) {
            moves.add(row + direction to col)
            if ((color == "white" && row == 6) || (color == "black" && row == 1)) {
                if (board.isEmpty(row + 2 * direction, col)) moves.add(row + 2 * direction to col)
            }
        }
        if (board.isOpponent(row + direction, col + 1, color)) moves.add(row + direction to col + 1)
        if (board.isOpponent(row + direction, col - 1, color)) moves.add(row + direction to col - 1)
        return moves
    }
}

class Rook(color: String) : Piece(color) {
    override val symbol = if (color == "white") 'R' else 'r'
    override fun validMoves(board: ChessBoard, pos: Pair<Int, Int>) = generateLineMoves(board, pos)
}

class Knight(color: String) : Piece(color) {
    override val symbol = if (color == "white") 'N' else 'n'
    override fun validMoves(board: ChessBoard, pos: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = pos
        val moves = listOf(
            row + 2 to col + 1, row + 2 to col - 1, row - 2 to col + 1, row - 2 to col - 1,
            row + 1 to col + 2, row + 1 to col - 2, row - 1 to col + 2, row - 1 to col - 2
        )
        return moves.filter { board.isValid(it) && !board.isSameColor(it, color) }
    }
}

class Bishop(color: String) : Piece(color) {
    override val symbol = if (color == "white") 'B' else 'b'
    override fun validMoves(board: ChessBoard, pos: Pair<Int, Int>) = generateDiagonalMoves(board, pos)
}

class Queen(color: String) : Piece(color) {
    override val symbol = if (color == "white") 'Q' else 'q'
    override fun validMoves(board: ChessBoard, pos: Pair<Int, Int>) =
        generateLineMoves(board, pos) + generateDiagonalMoves(board, pos)
}

class King(color: String) : Piece(color) {
    override val symbol = if (color == "white") 'K' else 'k'
    override fun validMoves(board: ChessBoard, pos: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = pos
        return (-1..1).flatMap { dRow -> (-1..1).map { dCol -> row + dRow to col + dCol } }
            .filter { it != pos && board.isValid(it) && !board.isSameColor(it, color) }
    }
}

class ChessBoard {
    val board = Array(8) { arrayOfNulls<Piece>(8) }
    var currentTurn = "white"

    init {
        setupBoard()
    }

    private fun setupBoard() {
        // Set up the white pieces
        board[0] = arrayOf(
            Rook("black"), Knight("black"), Bishop("black"), Queen("black"),
            King("black"), Bishop("black"), Knight("black"), Rook("black")
        )
        board[1] = Array(8) { Pawn("black") }
        board[6] = Array(8) { Pawn("white") }
        board[7] = arrayOf(
            Rook("white"), Knight("white"), Bishop("white"), Queen("white"),
            King("white"), Bishop("white"), Knight("white"), Rook("white")
        )
    }

    fun isEmpty(row: Int, col: Int) = board[row][col] == null
    fun isOpponent(row: Int, col: Int, color: String) =
        board[row][col]?.color != color && !isEmpty(row, col)

    fun isValid(pos: Pair<Int, Int>) = pos.first in 0..7 && pos.second in 0..7
    fun isSameColor(pos: Pair<Int, Int>, color: String) =
        board[pos.first][pos.second]?.color == color

    fun movePiece(start: Pair<Int, Int>, end: Pair<Int, Int>) {
        board[end.first][end.second] = board[start.first][start.second]
        board[start.first][start.second] = null
        currentTurn = if (currentTurn == "white") "black" else "white"
    }

    fun printBoard() {
        for (row in board) {
            println(row.joinToString(" ") { it?.symbol?.toString() ?: "." })
        }
    }

    fun collectValidMoves(color: String): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
        val moves = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
        for (row in 0..7) for (col in 0..7) {
            val piece = board[row][col] ?: continue
            if (piece.color == color) {
                val valid = piece.validMoves(this, row to col)
                moves.addAll(valid.map { (row to col) to it })
            }
        }
        return moves
    }
}

class SimpleAI(private val board: ChessBoard, private val color: String) {
    fun makeMove() {
        val validMoves = board.collectValidMoves(color)
        if (validMoves.isNotEmpty()) {
            val move = validMoves.random()
            board.movePiece(move.first, move.second)
            println("Enemy moves: ${move.first} -> ${move.second}")
        }
    }
}

fun startChessGame() {
    val board = ChessBoard()
    val ai = SimpleAI(board, "black")
    while (true) {
        board.printBoard()
        println("${board.currentTurn.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}'s turn. Enter your move (e.g., e2 e4):")
        val input = readlnOrNull() ?: break
        if (input.lowercase() == "x") break
        try {
            val (start, end) = input.split(" ").map { it.toPosition() }
            board.movePiece(start, end)
            ai.makeMove()
        } catch (e: Exception) {
            println("Invalid move: ${e.message}")
        }
    }
}

fun String.toPosition(): Pair<Int, Int> {
    val col = this[0] - 'a'
    val row = 8 - this[1].digitToInt()
    return row to col
}

fun generateLineMoves(board: ChessBoard, pos: Pair<Int, Int>): List<Pair<Int, Int>> {
    val directions = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    return generateMoves(board, pos, directions)
}

fun generateDiagonalMoves(board: ChessBoard, pos: Pair<Int, Int>): List<Pair<Int, Int>> {
    val directions = listOf(1 to 1, 1 to -1, -1 to 1, -1 to -1)
    return generateMoves(board, pos, directions)
}

fun generateMoves(board: ChessBoard, pos: Pair<Int, Int>, directions: List<Pair<Int, Int>>)
        : List<Pair<Int, Int>> {
    val (row, col) = pos
    val moves = mutableListOf<Pair<Int, Int>>()
    for ((dRow, dCol) in directions) {
        var newRow = row + dRow
        var newCol = col + dCol
        while (board.isValid(newRow to newCol) && board.isEmpty(newRow, newCol)) {
            moves.add(newRow to newCol)
            newRow += dRow
            newCol += dCol
        }
        if (board.isValid(newRow to newCol) && board.isOpponent(newRow, newCol, board.board[row][col]!!.color)) {
            moves.add(newRow to newCol)
        }
    }
    return moves
}