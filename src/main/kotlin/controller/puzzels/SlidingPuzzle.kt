package controller.puzzels

import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.Timer
import kotlin.math.abs

class SlidingPuzzle(image: BufferedImage, private val rows: Int, private val cols: Int) : JFrame() {
    private val tileSize = image.width / cols
    private val tiles: Array<Array<Tile?>>
    private var emptyTile: Tile? = null

    init {
        this.tiles = Array(rows) { arrayOfNulls(cols) }

        title = "Sliding Puzzle"
        setSize(cols * tileSize, rows * tileSize)
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = GridLayout(rows, cols)

        // Initialisiere die Kacheln
        initializeTiles(image)

        // Mische die Kacheln
        shuffleTiles()

        // Füge die Kacheln zum JFrame hinzu
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                add(tiles[i][j])
            }
        }

        isVisible = true
    }

    private fun initializeTiles(image: BufferedImage) {
        var tileIndex = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                // Erstelle Kacheln mit Teilbildern
                val tileImage = image.getSubimage(j * tileSize, i * tileSize, tileSize, tileSize)
                tiles[i][j] = Tile(tileImage, i, j, tileIndex++)
                tiles[i][j]!!.addActionListener(TileClickListener(i, j))
            }
        }
        // Leere Kachel
        emptyTile = tiles[rows - 1][cols - 1] // Die letzte Kachel ist leer
        emptyTile?.isEmpty = (true)
    }

    private fun shuffleTiles() {
        val tileList = ArrayList<Tile?>()
        for (i in 0 until rows) {
            tileList.addAll(Arrays.asList(*tiles[i]).subList(0, cols))
        }
        Collections.shuffle(tileList)

        // Setze die Kacheln nach dem Mischen wieder in die Matrix
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                tiles[i][j] = tileList[i * cols + j]
                tiles[i][j]!!.setPosition(i, j)
            }
        }
        emptyTile = tiles[rows - 1][cols - 1] // Die letzte Kachel ist leer
        emptyTile?.isEmpty = (true)
    }

    private inner class Tile(private val image: BufferedImage, var row: Int, var col: Int, index: Int) : JButton() {
        var isEmpty: Boolean = false
            set(isEmpty) {
                field = isEmpty
                isVisible = !isEmpty // Leere Kachel unsichtbar machen
            }

        init {
            icon = ImageIcon(image)
        }

        fun setPosition(row: Int, col: Int) {
            this.row = row
            this.col = col
        }

        fun moveTo(target: Tile?) {
            // Animationslogik hier
            val targetRow = target!!.row
            val targetCol = target.col
            val originalLocation = location
            val targetLocation = target.location

            // Animation
            val timer = Timer(20, null)
            timer.addActionListener(object : ActionListener {
                private val steps = 10
                private var step = 0

                override fun actionPerformed(e: ActionEvent) {
                    if (step < steps) {
                        val x = originalLocation.x + (targetLocation.x - originalLocation.x) * step / steps
                        val y = originalLocation.y + (targetLocation.y - originalLocation.y) * step / steps
                        setLocation(x, y)
                        step++
                    } else {
                        location = targetLocation
                        target.isEmpty = (true)
                        emptyTile?.isEmpty = (false)
                        timer.stop()
                    }
                }
            })
            timer.start()
        }

        override fun getPreferredSize(): Dimension {
            return Dimension(tileSize, tileSize)
        }
    }

    private inner class TileClickListener(private val row: Int, private val col: Int) : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            // Überprüfen, ob die Kachel benachbart zur leeren Kachel ist
            if ((abs((row - emptyTile!!.row).toDouble()) == 1.0 && col == emptyTile!!.col) ||
                (abs((col - emptyTile!!.col).toDouble()) == 1.0 && row == emptyTile!!.row)
            ) {
                tiles[row][col]!!.moveTo(emptyTile)
                emptyTile?.isEmpty = (false)
                emptyTile = tiles[row][col] // Update die leere Kachel
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                // Bild laden
                val image =
                    ImageIO.read(File("C:\\Users\\kaiha\\Desktop\\projects\\Protegard\\images\\a3277423-7433-4489-95f9-688643801708.jpg")) // Setze den Pfad zu deinem Bild
                val rows = 4 // Anzahl der Zeilen
                val cols = 4 // Anzahl der Spalten
                SlidingPuzzle(image, rows, cols)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}