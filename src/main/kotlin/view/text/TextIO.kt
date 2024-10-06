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
            val choice = scanner!!.nextLine()
            handleChoice(choice)
        }

    }


    private fun showOptions() {
        println("Type:")
        println("Help or h to show commands")
        println("Exit or x to exit program")
        println("The Game begins..")
    }

    private fun handleChoice(choice: String) {
        when (choice) {
            // "1" -> explore()
            // "2" -> talkToCharacter()
            "3" -> {
                System.exit(0)
            }

            else -> println("Invalid choice. Type a valid choice.")
        }
    }

    override fun addDialog(text: String, source: Identifieable) {
        print(source.name + ": " + text)
    }

    private fun print(text: String) {
        println(text)
    }
}