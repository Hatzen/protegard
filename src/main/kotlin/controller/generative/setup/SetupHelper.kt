package org.example.controller.generative.setup

import org.example.controller.generative.ChatGPTAdventure
import java.io.IOException

class SetupHelper {

    // TODO: If we want RAG but probably not needed so far?
    // https://ollama.com/library/nemotron-mini
    val MODEL = "llama3.2"
    private val COMMAND = "ollama"

    fun startOlama() {
        if (!isOllamaInstalled()) {
            println("Ollama not found. Attempting installation...")
            installOllama()
        }

        // Add shutdown hook to cleanly stop the server
        Runtime.getRuntime().addShutdownHook(Thread {
            println("Shutting down Ollama server...")
            runCommand(listOf(COMMAND, "stop", MODEL))
        })

        setEnvironmentVariables()
        startOllamaServer()


        while (true) {
            if (checkServerHealth(MODEL)) {
                println("Server is running with $MODEL")
                break
            } else {
                println("Server health check failed!")
            }
            Thread.sleep(3000) // Check every 3 seconds
        }
    }

    // Check if Ollama is installed
    fun isOllamaInstalled(): Boolean {
        return try {
            val process = ProcessBuilder("ollama", "--version")
                .inheritIO()
                .redirectErrorStream(true)
                .start()
            process.waitFor() == 0
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    // Install Ollama using winget (Windows) or curl (Linux/Mac)
    fun installOllama() {
        val os = System.getProperty("os.name").lowercase()
        val command = if (os.contains("win")) {
            listOf("winget", "install", "--id=Ollama.Ollama", "-e")
        } else {
            listOf("sh", "-c", "curl -fsSL https://ollama.com/install.sh | sh")
        }

        runCommand(command)?.let { println(it) }
    }

    // Set environment variables for Ollama
    fun setEnvironmentVariables() {
        val env = System.getenv()
        // env.entries.forEach { println("${it.key}=${it.value}") }

        // TODO: This sets the vars for this process, do we really want this? Does it lead to child processes properly set.
        // System.setProperty("OLLAMA_DEBUG", "1")
        System.setProperty("OLLAMA_HOST", "127.0.0.1:11434")
        // System.setProperty("OLLAMA_KEEP_ALIVE", "5m")
        System.setProperty("OLLAMA_MAX_LOADED_MODELS", "1")
        // System.setProperty("OLLAMA_MODELS", "/path/to/models")
    }

    // Start the Ollama server with llama3.2
    fun startOllamaServer() {
        // val command = listOf(COMMAND, "serve")
        // TODO: Remove. Obviously loaded via rest already. And these calls would block
        // val command = listOf(COMMAND, "run", "llama3.2")
        // runCommand(command)?.let { println(it) }
        // val command2 = listOf(COMMAND, "run", "llama3.2")
        // runCommand(command2)?.let { println(it) }

        // Get test response to start model without blocking process.
        ChatGPTAdventure().getDynamicResponse("Answer to test")
    }

    // Check server health by running `ollama ps` and looking for llama3.2
    fun checkServerHealth(model: String): Boolean {
        val output = runCommand(listOf(COMMAND, "ps"))
        return output?.contains(model) == true
    }

    // Helper function to run shell commands
    fun runCommand(command: List<String>): String? {
        return try {
            // TODO: debug only..
            println("Command run: $command")
            val process = ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()
            process.inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            println("Command failed: $command")
            e.printStackTrace()
            null
        }
    }
}