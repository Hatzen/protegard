package controller.puzzels.test.test

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
import kotlin.math.min
import kotlin.random.Random

// TODO: Remove
class SlidingPuzzle : Application() {
    private lateinit var buttons: Array<Array<Button>>
    private lateinit var originalImage: Image
    private var emptyRow = 0
    private var emptyCol = 0
    private var rows = 0
    private var cols = 0
    private var tileSize = 0 // Größe der Kacheln

    override fun start(primaryStage: Stage) {
        val fileChooser = FileChooser()
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"))

        val file = fileChooser.showOpenDialog(primaryStage) ?: return
        originalImage = Image(file.toURI().toString())

        // Bestimme die Anzahl der Zeilen und Spalten basierend auf der Bildgröße
        determineGridSize(originalImage.width.toInt(), originalImage.height.toInt())

        buttons = Array(rows) { Array(cols) { Button() } }

        val grid = GridPane()
        grid.alignment = Pos.CENTER
        grid.hgap = 2.0
        grid.vgap = 2.0

        // Initialisiere die Kacheln und mische sie
        initializeTiles(grid)
        shuffleTiles()

        val scene =
            Scene(grid, (cols * tileSize + 2 * grid.hgap).toDouble(), (rows * tileSize + 2 * grid.vgap).toDouble())
        primaryStage.title = "Sliding Puzzle"
        primaryStage.scene = scene
        primaryStage.show()
    }

    private fun determineGridSize(width: Int, height: Int) {
        // Berechne die maximale Anzahl der Zeilen und Spalten (maximal 10)
        val MAX = 3

        cols = min(MAX, width / 100) // Maximale Spaltenanzahl auf 10 setzen
        rows = min(MAX, height / 100) // Maximale Zeilenanzahl auf 10 setzen

        // Berechne die Kachelgröße, um gleich große Kacheln zu erhalten
        tileSize = min(width / cols, height / rows)
    }

    private fun initializeTiles(grid: GridPane) {
        var tileIndex = 0

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val button = Button()
                button.setPrefSize(tileSize.toDouble(), tileSize.toDouble())
                button.setOnAction {
                    if (moveTile(i, j)) {
                        button.text = ""
                        checkForWin() // Überprüfe nach jedem Zug, ob das Puzzle gelöst ist
                    }
                }
                buttons[i][j] = button
                grid.add(button, j, i)
                tileIndex++
            }
        }

        // Setze die Position der leeren Kachel (letzte Position)
        emptyRow = rows - 1
        emptyCol = cols - 1
        buttons[emptyRow][emptyCol].text = "" // Leere Kachel bleibt leer
    }

    private fun shuffleTiles() {
        val numbers = (0 until rows * cols).toMutableList()
        numbers.shuffle(Random)

        var index = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (index < numbers.size && numbers[index] != 0) {
                    buttons[i][j].graphic = createTileImage(numbers[index])
                    buttons[i][j].text = numbers[index].toString() // Zeige die Kachelnummer
                } else {
                    buttons[i][j].graphic = null // Setze auf leer
                    buttons[i][j].text = "" // Kachel bleibt leer
                }
                index++
            }
        }

        // Setze die Position der leeren Kachel
        emptyRow = numbers.indexOf(0) / cols
        emptyCol = numbers.indexOf(0) % cols
    }

    private fun createTileImage(tileNumber: Int): ImageView {
        val colIndex = (tileNumber % cols)
        val rowIndex = (tileNumber / cols)

        // Erstelle ein Bild der Kachel
        val tileImage =
            WritableImage(originalImage.pixelReader, colIndex * tileSize, rowIndex * tileSize, tileSize, tileSize)
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

    private fun checkForWin() {
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
