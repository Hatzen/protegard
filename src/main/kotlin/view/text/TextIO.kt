package org.example.view.text

import org.example.controller.GameController
import org.example.controller.GameController.getAllPeople
import org.example.controller.GameController.getAllRoomConnections
import org.example.controller.GameController.lookAround
import org.example.controller.IView
import org.example.controller.generative.ChatGPTAdventure
import org.example.model.interfaces.Identifieable
import org.example.view.text.TextCommands.*
import java.util.*

class TextIO(val translator: ChatGPTAdventure) : IView {
    private lateinit var scanner: Scanner

    override fun start() {
        showIntro()
        scanner = Scanner(System.`in`)

        while (true) {
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
            EXIT -> GameController.exit()
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
                val list: List<Identifieable> = lookAround()

                printIdentifiables(list)
            }

            LISTITEMS -> {
                printIdentifiables(GameController.getInventory())
            }

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
        val translatedText = translator.translate(text)
        println(translatedText)
    }
}