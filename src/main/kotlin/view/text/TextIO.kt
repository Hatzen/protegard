package org.example.view.text

import org.example.controller.GameController
import org.example.controller.GameController.getAllPeople
import org.example.controller.GameController.getAllRoomConnections
import org.example.controller.GameController.getAllRoomObjects
import org.example.controller.IView
import org.example.model.interfaces.Identifieable
import org.example.view.text.TextCommands.*
import java.util.*

class TextIO : IView {
    private lateinit var scanner: Scanner

    // TODO: remove
    private var lastMessage = ""

    fun start() {
        showIntro()
        GameController.init(this)
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
                println("Wrong parameters please try again.. Enter h for Help")
            }
        }

    }


    private fun showIntro() {
        println("Type:")
        println("Help or h to show commands")
        println("Exit or x to exit program")
        println("The Game begins..")
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
                val list: List<Identifieable> = getAllRoomConnections().map { it.toRoom }
                    .plus(getAllRoomObjects())
                    .plus(getAllPeople())

                // TODO: Move to Gamecontroller call?
                //   and call getRandomAnswerForLookingAround
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
        return identifieable.name.lowercase().startsWith(identifier.lowercase(), true)
    }

    private fun printHelp() {
        for (x in TextCommands.entries) {
            println("Command " + x.value.key + " shortcut " + x.value.shortcut + " minParameter " + x.value.numberOfIdentifiersMin + " maxParameter " + x.value.numberOfIdentifiersMax)
        }
    }

    private fun printIdentifiables(list: List<Identifieable>) {
        println("Available:")
        println(list.map { it.name }.joinToString { "$it, " })
    }

    override fun addText(text: String, source: Identifieable) {
        println(source.name + ": " + text)
    }

    override fun addText(text: String) {
        println(text)
    }


    override fun getChoice(): Int {
        do {
            try {
                return scanner.nextInt()
            } catch (ex: NoSuchElementException) {
                println("Not a valid choice, please type just the number.")
            }
        } while (true)
    }

    private fun print(text: String) {
        println(text)
    }
}