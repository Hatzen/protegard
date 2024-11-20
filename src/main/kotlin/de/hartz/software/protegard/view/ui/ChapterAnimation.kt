package de.hartz.software.protegard.view.ui

import de.hartz.software.protegard.view.ui.SoundHelper.generateBellSound
import de.hartz.software.protegard.view.ui.SoundHelper.playSound
import javax.swing.SwingUtilities
import kotlin.concurrent.thread
object ChapterAnimation {

    fun showChapter(i: Int) {
        Thread{
            val mysteriousSound = generateBellSound(frequency = 110.0, durationMs = 9000) // A (110 Hz) for 9 seconds
            playSound(mysteriousSound)
        }.start()
        SwingUtilities.invokeLater {
            val display = ChapterDisplay(i)
            display.isVisible = true
            display.isAlwaysOnTop = true

            // Automatically close after a delay (optional)
            thread {
                Thread.sleep(5000)
                display.isVisible = false
                display.dispose()
            }
        }
    }

}


fun main() {
    Thread{
        val mysteriousSound = generateBellSound(frequency = 110.0, durationMs = 9000) // A (440 Hz) for 5 seconds
        playSound(mysteriousSound)
    }.start()
    SwingUtilities.invokeLater {
        val display = ChapterDisplay(5)
        display.isVisible = true

        // Automatically close after a delay (optional)
        thread {
            Thread.sleep(5000)
            System.exit(0)
        }
    }
}