package de.hartz.software.protegard.controller.generative.setup

import de.hartz.software.protegard.controller.LoggerUtil.logger
import de.hartz.software.protegard.controller.generative.content.ChatGPTAdventure.Companion.API_URL
import de.hartz.software.protegard.controller.generative.content.ChatGPTAdventure.Companion.MODEL
import de.hartz.software.protegard.controller.generative.content.GenerativeService
import de.hartz.software.protegard.controller.generative.translation.TranslationGPT
import de.hartz.software.protegard.model.settings.Settings
import java.io.IOException

class SetupHelper {

    companion object {
        private val COMMAND = "ollama"


        // Check if Ollama is installed
        val isOllamaInstalled: Boolean by lazy {
            try {
                val process = ProcessBuilder("ollama", "--version")
                    .start()
                val versionText = process.inputStream.bufferedReader().use { it.readText() }
                logger.info(versionText)
                process.waitFor() == 0
            } catch (e: IOException) {
                logger.error { e }
                false
            }
        }
    }

    fun startOlama() {
        if (!isOllamaInstalled) {
            logger.warn("Ollama not found. Attempting installation...")
            installOllama()
        }

        // Add shutdown hook to cleanly stop the server
        Runtime.getRuntime().addShutdownHook(Thread {
            logger.warn("Shutting down Ollama server...")
            runCommand(listOf(COMMAND, "stop", MODEL))
            runCommand(listOf(COMMAND, "stop", TranslationGPT.MODEL))

            // TODO: server still needs to be killed:
            // https://github.com/ollama/ollama/issues/690#issuecomment-1998454215
            // Get-Process | Where-Object {$_.ProcessName -like '*ollama*'} | Stop-Process
        })

        setEnvironmentVariables()
        startOllamaServer()


        while (true) {
            // TODO: Locally only one model at a time willl run..  && checkServerHealth(TranslationGPT.MODEL)
            if (checkServerHealth(MODEL)) {
                logger.info("Server is running with $MODEL")
                break
            } else {
                logger.debug("Server health check failed!")
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

        runCommand(command)?.let { logger.debug(it) }
    }

    // Set environment variables for Ollama
    private fun setEnvironmentVariables() {
        // TODO: This is quiet useless.. we need to set env variables via system..
        // System.setProperty("OLLAMA_DEBUG", "1")
        System.setProperty("OLLAMA_HOST", "127.0.0.1:11434")
        System.setProperty("OLLAMA_KEEP_ALIVE", "10m")
        // TODO: Wont work with 8GB VRAM.
        System.setProperty("OLLAMA_MAX_LOADED_MODELS", "3")
        // System.setProperty("OLLAMA_MODELS", "/path/to/models")

        // val env = System.getenv()
        // env.entries.forEach { println("${it.key}=${it.value}") }
    }

    // Start the Ollama server with llama3.2
    private fun startOllamaServer() {
        // Needed otherwise run ollama will lead to Error:
        // Head "http://127.0.0.1:11434/": dial tcp 127.0.0.1:11434: connectex: Connection refused
        Thread {
            // Start ollama
            var command = listOf(COMMAND, "serve")
            runCommand(command)?.let { logger.info(it) }
        }.start()

        /*Thread {
            // Get/update image and run
            var command = listOf(COMMAND, "pull", MODEL)
            runCommand(command)?.let { logger.info(it) }
            command = listOf(COMMAND, "run", MODEL)
            runCommand(command)?.let { logger.info(it) }
        }.start()*/

        Thread {
            // Get/update image and run
            var command = listOf(COMMAND, "pull", TranslationGPT.MODEL)
            runCommand(command)?.let { logger.info(it) }
            command = listOf(COMMAND, "run", TranslationGPT.MODEL)
            runCommand(command)?.let { logger.info(it) }
        }.start()

        GenerativeService(API_URL, MODEL).generateStoryIndependentStuff("Answer to test", Settings.DEFAULT_LANGUAGE)
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
            logger.debug("Command run: {}", command)
            process.inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            logger.warn("Command failed: $command", e)
            null
        }
    }
}