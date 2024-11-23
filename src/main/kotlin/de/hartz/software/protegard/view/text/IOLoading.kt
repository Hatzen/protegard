package de.hartz.software.protegard.view.text

class IOLoading : Thread() {
    private var running = true
    private var ended = false

    init {
        start()
    }

    fun stopAnimation() {
        running = false
        while (!ended) {
            sleep(200)
        }
    }

    override fun run() {
        val spinner = listOf("|", "/", "-", "\\") // Spinner characters
        val intervalMs = 100L
        var index = 0
        while (running) {
            print("\rLoading ${spinner[index]}") // \r moves the cursor back to overwrite the line
            sleep(intervalMs)
            index = (index + 1) % spinner.size // Cycle through spinner characters
        }
        print("\r\n") // Clear spinner
        ended = true
    }

}