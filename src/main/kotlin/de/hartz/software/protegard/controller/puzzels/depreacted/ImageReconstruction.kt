package de.hartz.software.protegard.controller.puzzels.depreacted

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JPanel

// TODO: Setup properly
class ImageReconstruction : JFrame("Image Reconstruction") {
    private val filenameList: MutableList<String> = ArrayList()
    private val gridSize = 10
    private var imagePanel: JPanel? = null

    init {
        this.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                System.exit(0)
            }
        })
        createGUI()
    }

    private fun createGUI() {
        // Input panel with load button

        val inputPanel = JPanel()
        val loadButton = JButton("Load Images")
        loadButton.addActionListener {
            val filename = JOptionPane.showInputDialog("Enter the path to an image file (in .jpg or .png format)")
            if (!filename.isEmpty()) {
                filenameList.add(filename)
                // createImagePanel();
            }
        }

        inputPanel.add(loadButton)

        // Image panel
        imagePanel = object : JPanel() {
            override fun paintComponent(g: Graphics) {
                super.paintComponent(g)
                val width = 0
                val height = 0

                if (filenameList.size == 0) {
                    g.drawString("No images loaded", 50, 20)
                    return
                }

                val rows = gridSize
                val columns = gridSize

                val imgs = arrayOfNulls<BufferedImage>(rows * columns)

                for (i in 0 until rows) {
                    for (j in 0 until columns) {
                        try {
                            imgs[i * columns + j] = ImageIO.read(File(filenameList[i * columns + j]))
                        } catch (ex: IOException) {
                            continue
                        }
                    }
                }

                var x = 10
                var y = 20

                for (i in 0 until rows) {
                    for (j in 0 until columns) {
                        if (imgs[i * columns + j] != null) {
                            g.drawImage(imgs[i * columns + j], x, y, null)
                            x += gridSize
                        }
                        if (x > getWidth() - imgs[rows * columns - 1]!!.width) {
                            x = 10
                            y += gridSize
                        }
                    }
                }
            }
        }

        val imagePanel2 = imagePanel
        imagePanel2!!.preferredSize = Dimension(800, 600)

        // Main panel
        val mainPanel = JPanel()
        mainPanel.layout = BorderLayout()
        mainPanel.add(inputPanel, BorderLayout.NORTH)
        mainPanel.add(imagePanel, BorderLayout.CENTER)

        this.contentPane = mainPanel
        this.pack()
        this.isVisible = true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ImageReconstruction()
        }
    }
}