package org.example.view.text

import org.example.controller.GameController
import org.example.controller.GameController.getAllPeople
import org.example.controller.GameController.getAllRoomConnections
import org.example.controller.GameController.getAllRoomObjects
import org.example.controller.IView
import org.example.model.interfaces.Identifieable
import org.example.model.scenario.Rooms
import org.example.view.text.TextCommands.*
import java.util.*

class TextIO: IView {
    private var scanner: Scanner? = null

    // TODO: remove
    private var lastMessage = ""

    fun start() {
        GameController.init(this)
        scanner = Scanner(System.`in`)
        showOptions()
        while (true) {
            val choice = scanner!!.nextLine().lowercase().trim()

            try {
                // Split all words except within ""
                val reg = "\"[^\"]*\"|\\S+"
                val commandsAndParameters = choice.split(reg)
                handleChoice(commandsAndParameters)
            } catch (ex: IllegalArgumentException) {
                println("Wrong parameters please try again.. Enter h for Help")
            }
        }

    }


    private fun showOptions() {
        println("Type:")
        println("Help or h to show commands")
        println("Exit or x to exit program")
        println("The Game begins..")
    }

    private fun handleChoice(commandsAndParameters: List<String>) {
        val command = commandsAndParameters.first()
        val foundCommand = TextCommands.entries.find { it.value.key.lowercase() == command || it.value.shortcut.lowercase() == command }
        requireNotNull(foundCommand)
        val parameters = commandsAndParameters.subList(1, commandsAndParameters.size)
        val properParameterCount = IntRange(foundCommand.value.numberOfIdentifiersMin, foundCommand.value.numberOfIdentifiersMax)
            .contains(parameters.size)
        require(properParameterCount)

        when (foundCommand) {
            EXIT -> GameController.exit()
            HELP -> printHelp()
            SAVE -> TODO()
            LOAD -> TODO()
            GOTO -> {
                val room = GameController.getAllRoomConnections().find { isMatch(it.toRoom, parameters[0]) }
                requireNotNull(room)
                GameController.goto(room)
            }
            SPEAKTO -> {
                val character = GameController.getAllPeople().find { isMatch(it, parameters[0]) }
                requireNotNull(character)
                GameController.startDialog(character)
            }
            USEITEM -> {
                val item = GameController.getInventory().find { isMatch(it, parameters[0]) }
                requireNotNull(item)
                item.interact()
            }
            COMBINEITEM -> {
                val item1 = GameController.getInventory().find { isMatch(it, parameters[0]) }
                val item2 = GameController.getInventory().find { isMatch(it, parameters[1]) }
                requireNotNull(item1)
                requireNotNull(item2)
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

                printIdentifiables(list)
            }
            LISTITEMS -> {
                printIdentifiables(GameController.getInventory())
            }
            LISTCONNECTIONS ->
                printIdentifiables(GameController.getAllRoomConnections().map { it.toRoom })
            LISTPEOPLE ->
                printIdentifiables(GameController.getAllPeople())
            LISTACTIONS -> TODO()
        }
    }

    private fun isMatch(identifieable: Identifieable, identifier: String): Boolean {
        return identifieable.name.lowercase().startsWith(identifier.lowercase(), true)
    }

    private fun printHelp() {
        for(x in TextCommands.entries) {
            println("Command " + x.value.key + " shortcut " + x.value.shortcut + " minParameter " + x.value.numberOfIdentifiersMin  + " maxParameter " + x.value.numberOfIdentifiersMax)
        }
    }

    private fun printIdentifiables(list: List<Identifieable>) {
        println("Available:")
        println(list.map { it.name }.joinToString { "$it, " })
    }

    override fun addDialog(text: String, source: Identifieable) {
        print(source.name + ": " + text)
    }

    private fun print(text: String) {
        println(text)
    }
}