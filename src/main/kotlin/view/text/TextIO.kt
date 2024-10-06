package org.example.view.text

import org.example.controller.GameController
import org.example.controller.IView
import org.example.model.interfaces.Identifieable
import java.util.*

class TextIO: IView {
    private var scanner: Scanner? = null

    private var lastMessage = ""

    fun start() {
        GameController.view = this
        scanner = Scanner(System.`in`)
        showOptions()
        while (true) {
            val choice = scanner!!.nextLine().lowercase().trim()

            // Split all words except within ""
            val reg = "\"[^\"]*\"|\\S+"
            val commandsAndParameters = choice.split(reg)
            handleChoice(commandsAndParameters)
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
        require(!properParameterCount)


    }

    override fun addDialog(text: String, source: Identifieable) {
        print(source.name + ": " + text)
    }

    private fun print(text: String) {
        println(text)
    }
}