package org.example.controller.puzzels

/*
// TODO: Why is javafx not available anymore after upgrading and downgrading jvm version from 11 to 15
import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.image.WritableImage
import javafx.scene.layout.GridPane
import javafx.stage.FileChooser
import javafx.stage.Stage
import kotlin.random.Random

// TODO: Proper API. Proper shuffle (make random movements instead of shuffle). Fix winning with 1 place behind
class SlidingPuzzle : Application() {
    private lateinit var buttons: Array<Array<Button>>
    private lateinit var originalImage: Image
    private var emptyRow = 0
    private var emptyCol = 0
    private val rows = 2 // Statisch auf 2 Zeilen setzen
    private val cols = 2 // Statisch auf 2 Spalten setzen
    private val tileSize = 100 // Größe der Kacheln in Pixel

    override fun start(primaryStage: Stage) {
        val fileChooser = FileChooser()
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"))

        val file = fileChooser.showOpenDialog(primaryStage) ?: return
        originalImage = Image(file.toURI().toString())

        buttons = Array(rows) { Array(cols) { Button() } }

        val grid = GridPane()
        grid.alignment = Pos.CENTER
        grid.hgap = 2.0
        grid.vgap = 2.0

        // Initialisiere die Kacheln
        initializeTiles(grid)
        shuffleTiles() // Mische die Kacheln

        val scene =
            Scene(grid, (cols * tileSize + 2 * grid.hgap).toDouble(), (rows * tileSize + 2 * grid.vgap).toDouble())
        primaryStage.title = "Sliding Puzzle"
        primaryStage.scene = scene
        primaryStage.show()
    }

    private fun initializeTiles(grid: GridPane) {
        var tileIndex = 0

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val button = Button()
                button.setPrefSize(tileSize.toDouble(), tileSize.toDouble())
                button.setOnAction {
                    if (moveTile(i, j)) {
                        checkForWin() // Überprüfe nach jedem Zug, ob das Puzzle gelöst ist
                    }
                }
                buttons[i][j] = button
                grid.add(button, j, i)

                // Setze die Grafik der Kacheln
                if (tileIndex < cols * rows - 1) { // Letzte Kachel bleibt leer
                    button.graphic = createTileImage(tileIndex)
                    button.text = (tileIndex + 1).toString() // Zeige die Kachelnummer
                } else {
                    button.graphic = null // Setze auf leer
                    button.text = "" // Kachel bleibt leer
                }
                tileIndex++
            }
        }

        // Setze die Position der leeren Kachel
        emptyRow = rows - 1
        emptyCol = cols - 1
    }

    private fun createTileImage(tileNumber: Int): ImageView {
        val colIndex = (tileNumber % cols)
        val rowIndex = (tileNumber / cols)

        // Berechne die richtigen Pixelkoordinaten für die Kachel im Originalbild
        val tileImage = WritableImage(
            originalImage.pixelReader,
            colIndex * (originalImage.width.toInt() / cols),
            rowIndex * (originalImage.height.toInt() / rows),
            originalImage.width.toInt() / cols,
            originalImage.height.toInt() / rows
        )
        val imageView = ImageView(tileImage)
        imageView.fitWidth = tileSize.toDouble()
        imageView.fitHeight = tileSize.toDouble()
        imageView.isPreserveRatio = true

        return imageView
    }

    private fun moveTile(row: Int, col: Int): Boolean {
        if ((row == emptyRow && (col == emptyCol - 1 || col == emptyCol + 1)) ||
            (col == emptyCol && (row == emptyRow - 1 || row == emptyRow + 1))
        ) {
            // Bewege die Kachel
            buttons[emptyRow][emptyCol].graphic = buttons[row][col].graphic
            buttons[emptyRow][emptyCol].text = buttons[row][col].text
            buttons[row][col].graphic = null
            buttons[row][col].text = ""

            // Aktualisiere die leere Kachel
            emptyRow = row
            emptyCol = col
            return true
        }
        return false
    }

    private fun shuffleTiles() {
        val totalTiles = rows * cols
        val indices = (0 until totalTiles - 1).toMutableList() // Letzte Kachel bleibt leer
        indices.shuffle(Random(System.nanoTime()))

        // Füge die leere Kachel (letzte Position)
        indices.add(totalTiles - 1)

        // Mische die Kacheln und lege die leere Kachel am Ende
        var tileIndex = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val tileNum = indices[tileIndex]
                if (tileNum < totalTiles - 1) { // Letzte Kachel bleibt leer
                    buttons[i][j].graphic = createTileImage(tileNum)
                    buttons[i][j].text = (tileNum + 1).toString() // Zeige die Kachelnummer
                } else {
                    buttons[i][j].graphic = null // Setze auf leer
                    buttons[i][j].text = "" // Kachel bleibt leer
                }
                tileIndex++
            }
        }

        // Setze die Position der leeren Kachel
        emptyRow = rows - 1
        emptyCol = cols - 1
    }

    private fun checkForWin() {
        // TODO: gelöst 1 zu früh
        var expectedTile = 1

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (i == emptyRow && j == emptyCol) continue // Leere Kachel ignorieren
                if (buttons[i][j].text.toIntOrNull() != expectedTile) {
                    return // Wenn nicht alle Kacheln in der richtigen Reihenfolge sind
                }
                expectedTile++
            }
        }

        // Wenn das Puzzle gelöst ist, zeige einen Dialog an
        showWinDialog()
    }

    private fun showWinDialog() {
        val alert = Alert(AlertType.INFORMATION)
        alert.title = "Puzzle Gelöst!"
        alert.headerText = null
        alert.contentText = "Herzlichen Glückwunsch! Du hast das Puzzle erfolgreich gelöst!"
        alert.showAndWait()
    }
}

fun main() {
    Application.launch(SlidingPuzzle::class.java)
}
*/