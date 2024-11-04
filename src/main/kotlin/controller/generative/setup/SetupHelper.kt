package org.example.controller.generative.setup

import controller.generative.ChatService
import org.example.controller.generative.ChatGPTAdventure.Companion.API_URL
import org.example.controller.generative.ChatGPTAdventure.Companion.MODEL
import org.example.model.settings.Settings
import java.io.IOException

class SetupHelper {

    companion object {
        // TODO: If we want RAG but probably not needed so far?
        // https://ollama.com/library/nemotron-mini
        private val COMMAND = "ollama"


        // Check if Ollama is installed
        val isOllamaInstalled: Boolean by lazy {
            try {
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
    }

    fun startOlama() {
        if (!isOllamaInstalled) {
            println("Ollama not found. Attempting installation...")
            installOllama()
        }

        // Add shutdown hook to cleanly stop the server
        Runtime.getRuntime().addShutdownHook(Thread {
            println("Shutting down Ollama server...")
            runCommand(listOf(COMMAND, "stop", MODEL))

            // TODO: server still needs to be killed:
            // https://github.com/ollama/ollama/issues/690#issuecomment-1998454215
            // Get-Process | Where-Object {$_.ProcessName -like '*ollama*'} | Stop-Process
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
        // TODO: Do in background
        // Needed otherwise run ollama will lead to Error:
        // Head "http://127.0.0.1:11434/": dial tcp 127.0.0.1:11434: connectex: Connection refused
        Thread {
            val command = listOf(COMMAND, "serve")
            runCommand(command)?.let { println(it) }
        }

        // Get test response to start model without blocking process.
        ChatService(API_URL, MODEL).generateStoryIndependentStuff("Answer to test", Settings.DEFAULT_LANGUAGE)
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
            val process = ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()
            println("Command run: $command")
            process.inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            println("Command failed: $command")
            e.printStackTrace()
            null
        }
    }
}