package de.hartz.software.protegard.view.text

import kotlin.concurrent.thread

fun showLoadingAnimation(durationMs: Long = 5000, intervalMs: Long = 100) {
    val spinner = listOf("|", "/", "-", "\\") // Spinner characters
    val endTime = System.currentTimeMillis() + durationMs

    thread {
        var index = 0
        while (System.currentTimeMillis() < endTime) {
            // Print spinner character
            print("\rLoading ${spinner[index]}") // \r moves the cursor back to overwrite the line
            Thread.sleep(intervalMs)
            index = (index + 1) % spinner.size // Cycle through spinner characters
        }
        print("\rLoading complete!     \n") // Clear spinner and print completion message
    }.join() // Wait for the animation thread to finish
}

fun main() {
    showLoadingAnimation(durationMs = 5000) // Runs for 5 seconds
}