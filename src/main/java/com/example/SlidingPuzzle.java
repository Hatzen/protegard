package com.example;

// BufferedImage image = ImageIO.read(new File("C:\\Users\\kaiha\\Desktop\\projects\\Protegard\\images\\4x4.jpg")); // Setze den Pfad zu deinem Bild


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SlidingPuzzle extends JFrame {
    private final int rows;
    private final int cols;
    private final int tileSize;
    private final Tile[][] tiles;
    private Tile emptyTile;

    public SlidingPuzzle(BufferedImage image, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.tileSize = image.getWidth() / cols; // Angenommen, das Bild ist quadratisch
        this.tiles = new Tile[rows][cols];

        setTitle("Sliding Puzzle");
        setSize(cols * tileSize, rows * tileSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(rows, cols));

        // Initialisiere die Kacheln
        initializeTiles(image);
        shuffleTiles();

        // Füge die Kacheln zum JFrame hinzu
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                add(tiles[i][j]);
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            // Bild laden
            BufferedImage image = ImageIO.read(new File("C:\\Users\\kaiha\\Desktop\\projects\\Protegard\\images\\4x4.jpg")); // Setze den Pfad zu deinem Bild
            int rows = 4; // Anzahl der Zeilen
            int cols = 4; // Anzahl der Spalten
            new SlidingPuzzle(image, rows, cols);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeTiles(BufferedImage image) {
        int tileIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Erstelle Kacheln mit Teilbildern
                BufferedImage tileImage = image.getSubimage(j * tileSize, i * tileSize, tileSize, tileSize);
                tiles[i][j] = new Tile(tileImage, i, j, tileIndex++);
                tiles[i][j].addActionListener(new TileClickListener(i, j));
            }
        }
        // Leere Kachel
        emptyTile = tiles[rows - 1][cols - 1]; // Die letzte Kachel ist leer
        emptyTile.setEmpty(true);
    }

    private void shuffleTiles() {
        // Mischen der Kacheln
        ArrayList<Tile> tileList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            tileList.addAll(Arrays.asList(tiles[i]).subList(0, cols));
        }
        Collections.shuffle(tileList);

        // Setze die Kacheln nach dem Mischen wieder in die Matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = tileList.get(i * cols + j);
                tiles[i][j].setPosition(i, j);
            }
        }
        emptyTile = tiles[rows - 1][cols - 1]; // Die letzte Kachel ist leer
        emptyTile.setEmpty(true);
    }

    private class Tile extends JButton {
        private final BufferedImage image;
        private final int index;
        private int row, col;
        private boolean isEmpty = false;

        public Tile(BufferedImage image, int row, int col, int index) {
            this.image = image;
            this.row = row;
            this.col = col;
            this.index = index;

            // Setze die Kachel-Beschriftung
            setText(index < rows * cols - 1 ? String.valueOf(index + 1) : ""); // Letzte Kachel bleibt leer
            setFont(new Font("Arial", Font.BOLD, 24));
            setForeground(Color.RED);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setIcon(new ImageIcon(image));
        }

        public void setPosition(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean isEmpty() {
            return isEmpty;
        }

        public void setEmpty(boolean isEmpty) {
            this.isEmpty = isEmpty;
            setVisible(!isEmpty); // Leere Kachel unsichtbar machen
        }

        public void swapWith(Tile target) {
            // Swap this tile with the target tile
            int tempRow = this.row;
            int tempCol = this.col;
            this.row = target.row;
            this.col = target.col;

            // Update the display
            this.setVisible(!this.isEmpty);
            target.setVisible(!target.isEmpty);
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(tileSize, tileSize);
        }
    }

    private class TileClickListener implements ActionListener {
        private final int row;
        private final int col;

        public TileClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Überprüfen, ob die Kachel benachbart zur leeren Kachel ist
            if ((Math.abs(row - emptyTile.getRow()) == 1 && col == emptyTile.getCol()) ||
                    (Math.abs(col - emptyTile.getCol()) == 1 && row == emptyTile.getRow())) {
                // Kachel mit leerer Kachel tauschen
                tiles[row][col].swapWith(emptyTile);
                emptyTile.setEmpty(false);
                emptyTile = tiles[row][col]; // Update die leere Kachel
                emptyTile.setEmpty(true);
            }
        }
    }
}