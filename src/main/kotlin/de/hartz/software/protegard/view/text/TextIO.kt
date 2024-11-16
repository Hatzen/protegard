package de.hartz.software.protegard.view.text

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.controller.GameController.getAllPeople
import de.hartz.software.protegard.controller.GameController.getAllRoomConnections
import de.hartz.software.protegard.controller.GameController.lookAround
import de.hartz.software.protegard.controller.IView
import de.hartz.software.protegard.controller.generative.ChatGPTAdventure
import de.hartz.software.protegard.model.interfaces.Identifieable
import de.hartz.software.protegard.view.text.TextCommands.*
import okhttp3.internal.connection.RealCall
import java.util.*
import java.util.concurrent.ThreadPoolExecutor

class TextIO(val translator: ChatGPTAdventure) : IView {
    private val scanner: Scanner = Scanner(System.`in`)
    private var scanInput = true

    override fun listenForUserInput() {
        showIntro()

        while (scanInput) {
            val choice = scanner.nextLine().lowercase().trim()

            try {
                // Split all words except within ""
                val reg = "\"[^\"]*\"|\\S+"
                val commandsAndParameters = Regex(reg).findAll(choice).map { it.value }.toList()
                if (commandsAndParameters.isEmpty()) {
                    continue // User pressed enter.
                }
                handleChoice(commandsAndParameters)
            } catch (ex: IllegalArgumentException) {
                // TODO: Remove when debugging finished. Overall logging would be nice but is just overhead?
                ex.printStackTrace()
                tellUser("Wrong parameters please try again.. Enter h for Help")
            }
        }

    }


    private fun showIntro() {
        tellUser("Type:")
        tellUser("Help or h to show commands")
        tellUser("Exit or x to exit program")
        tellUser("The Game begins..")
    }

    private fun handleChoice(commandsAndParameters: List<String>) {
        val command = commandsAndParameters.first()
        val foundCommand =
            TextCommands.entries.find { it.value.key.lowercase() == command || it.value.shortcut.lowercase() == command }
        requireNotNull(foundCommand, { "$command is not a valid command. see h" })
        val parameters = commandsAndParameters.subList(1, commandsAndParameters.size)
        val validParameterCount =
            IntRange(foundCommand.value.numberOfIdentifiersMin, foundCommand.value.numberOfIdentifiersMax)
        val actualParameterCount = parameters.size
        val properParameterCount = validParameterCount.contains(actualParameterCount)
        require(properParameterCount) { "$actualParameterCount vs $validParameterCount" }

        when (foundCommand) {
            EXIT -> {
                scanInput = false
                GameController.exit()
            }
            HELP -> printHelp()
            //SAVE -> TODO()
            // LOAD -> TODO()
            GOTO -> {
                val room = getAllRoomConnections().find { isMatch(it.toRoom, parameters[0]) }
                requireNotNull(room) { " the entered room is not reachable or defined." }
                GameController.goto(room)
            }

            SPEAKTO -> {
                val character = getAllPeople().find { isMatch(it, parameters[0]) }
                requireNotNull(character)
                GameController.startDialog(character)
            }

            USEITEM -> {
                val item = GameController.getInventory().find { isMatch(it, parameters[0]) }
                requireNotNull(item)
                // TODO: Move to Gamecontroller call?
                item.interact()
            }

            COMBINEITEM -> {
                val item1 = GameController.getInventory().find { isMatch(it, parameters[0]) }
                val item2 = GameController.getInventory().find { isMatch(it, parameters[1]) }
                requireNotNull(item1)
                requireNotNull(item2)

                // TODO: Move to Gamecontroller call?
                // Burn Paper or combine pen with paper
                item1.combine(item2)
            }

            DOACTION -> {
                // TODO: item on people, like kill them or item on objects, like burn them
            }

            LOOKAROUND -> {
                lookAround()
            }

            LISTITEMS -> {
                printIdentifiables(GameController.getInventory())
            }

            // TODO: The game would be more immersive if we only use look around..
            LISTCONNECTIONS ->
                printIdentifiables(getAllRoomConnections().map { it.toRoom })

            LISTPEOPLE ->
                printIdentifiables(getAllPeople())

            LISTACTIONS -> TODO()
        }
    }

    private fun isMatch(identifieable: Identifieable, identifier: String): Boolean {
        return identifieable.fullname.lowercase().startsWith(identifier.lowercase(), true)
    }

    private fun printHelp() {
        tellUser("Type any of the following commands not case sensitive, either full or the shortcut letters. The parameter are ids of rooms, items or people. Not case sensitiv as well. Especially you do not have to type the full name, but only the first letters until it is not ambigous.")
        for (x in TextCommands.entries) {
            tellUser("Command " + x.value.key + " shortcut " + x.value.shortcut + " minParameter " + x.value.numberOfIdentifiersMin + " maxParameter " + x.value.numberOfIdentifiersMax)
        }
    }

    private fun printIdentifiables(list: List<Identifieable>) {
        tellUser("Available:")
        tellUser(list.map { it.fullname }.joinToString { "$it, " })
    }

    override fun addText(text: String, source: Identifieable) {
        tellUser(source.fullname + ": " + text)
    }

    override fun addText(text: String) {
        tellUser(text)
    }


    override fun getChoice(): Int {
        do {
            try {
                return scanner.nextInt()
            } catch (ex: NoSuchElementException) {
                tellUser("Not a valid choice, please type just the number.")
            }
        } while (true)
    }

    private fun tellUser(text: String) {
        // TODO:  //translator.translate(text) leads to nullpointer for some reason
        /*
        java.lang.NullPointerException: Cannot invoke "dev.langchain4j.model.output.Response.content()" because "response" is null
            at dev.langchain4j.service.AiServiceStreamingResponseHandler.onComplete(AiServiceStreamingResponseHandler.java:75)
            at dev.langchain4j.model.ollama.OllamaClient$2.onResponse(OllamaClient.java:180)
            at retrofit2.OkHttpCall$1.onResponse(OkHttpCall.java:161)
            at okhttp3.internal.connection.RealCall$AsyncCall.run(RealCall.kt:519)
            at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
            at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
            at java.base/java.lang.Thread.run(Thread.java:1583)
         */
        val translatedText = translator.translate(text) //
        println(translatedText)
    }
}