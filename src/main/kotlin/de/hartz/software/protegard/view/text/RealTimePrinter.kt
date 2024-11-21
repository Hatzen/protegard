package de.hartz.software.protegard.view.text

import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

class RealTimePrinter(private val delayMillis: Long = 10L) {
    private val messageQueue = LinkedBlockingQueue<String>()
    private val workerThread: Thread

    init {
        // Start a dedicated thread to process messages
        workerThread = thread(start = true) {
            while (!Thread.currentThread().isInterrupted) {
                try {
                    val message = messageQueue.take() // Blocks until a message is available
                    for (char in message) {
                        print(char)
                        System.out.flush() // Ensure immediate output
                        Thread.sleep(delayMillis)
                    }
                    println() // Move to the next line after each message
                } catch (e: InterruptedException) {
                    Thread.currentThread().interrupt() // Exit loop on interruption
                }
            }
        }
    }

    fun printMessage(message: String) {
        messageQueue.put(message) // Add message to the queue
    }

    fun shutdown() {
        workerThread.interrupt() // Gracefully stop the worker thread
    }
}

fun main() {
    val printer = RealTimePrinter(20L)

    // Example usage
    thread { printer.printMessage("Hello, World!") }
    thread { printer.printMessage("This is message 2.") }
    thread { printer.printMessage("Another parallel message.") }

    // Give some time for all messages to finish printing
    Thread.sleep(3000)
    printer.shutdown()
}