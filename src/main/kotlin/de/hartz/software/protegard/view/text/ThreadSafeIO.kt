package de.hartz.software.protegard.view.text

import java.util.*
import java.util.concurrent.LinkedBlockingQueue

class ThreadSafeIO : Thread() {
    private val inputQueue = LinkedBlockingQueue<String>()
    var running = true
    private var acceptInput = false

    init {
        start()
    }

    fun exit() {
        running = false
        acceptInput = true
    }

    fun getLine(): String {
        print("Enter a command> ")
        acceptInput = true
        return inputQueue.take().lowercase().trim()
    }

    fun getMultipleChoice(): List<Int> {
        print("Enter a list of numbers comma seperated> ")
        acceptInput = true
        try {
            return inputQueue.take()
                .split(",")
                .mapNotNull { it.trim().toIntOrNull() }
        } catch (ex: Exception) {
            throw NoSuchElementException()
        }
    }

    fun getChoice(): Int {
        print("Enter a number> ")
        acceptInput = true
        try {
            return Integer.parseInt(inputQueue.take())
        } catch (ex: Exception) {
            throw NoSuchElementException()
        }
    }


    override fun run() {
        val stream = System.`in`
        val scanner = Scanner(stream)
        while (running) {
            try {
                val input = scanner.nextLine()
                // Avoid issues with entering further lines when last input is not processed yet.
                acceptInput = false
                inputQueue.put(input)
                while (!acceptInput) {
                    sleep(200)
                }
            } catch (ex: InterruptedException) {
                currentThread().interrupt()
                break
            }
        }
        scanner.close()
        stream.close()
    }

}