package de.hartz.software.protegard.view.ui

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.JPanel

class ChapterDisplay(val chapter: Int) : JFrame() {

    init {
        // Configure the JFrame
        isUndecorated = true // Remove window decorations
        defaultCloseOperation = EXIT_ON_CLOSE
        background = Color(0, 0, 0, 0) // Transparent background
        setSize(800, 400)
        setLocationRelativeTo(null) // Center the window on the screen

        // Add the custom panel for rendering the styled text
        contentPane = object : JPanel() {
            override fun paintComponent(g: Graphics) {
                super.paintComponent(g)
                val g2d = g as Graphics2D
                renderText(g2d)
            }
        }.apply {
            background = Color(0, 0, 0, 0) // Semi-transparent black background
        }

        // Make sure the window can close cleanly
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                System.exit(0)
            }
        })
    }

    private fun renderText(g2d: Graphics2D) {
        val text = "Chapter $chapter"

        // Enable anti-aliasing for smooth text rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        // Draw shadow
        val shadowColor = Color(0, 0, 0, 150) // Semi-transparent black
        g2d.color = shadowColor
        g2d.font = getPreferredFont("Old English Text MT", "Chiller", "Verdana", "Arial")
        g2d.drawString(text, 205, 205) // Shadow offset

        // Draw main text
        g2d.color = Color.WHITE
        g2d.drawString(text, 200, 200)
    }

    private fun getPreferredFont(vararg fonts: String): Font {
        val availableFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().availableFontFamilyNames
        for (fontName in fonts) {
            if (fontName in availableFonts) {
                return Font(fontName, Font.BOLD, 72)
            }
        }
        // Fallback to default font if none of the preferred fonts are available
        return Font("Sans-Serif", Font.BOLD, 72)
    }
}