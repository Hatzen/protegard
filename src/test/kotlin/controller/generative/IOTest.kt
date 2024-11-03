package controller.generative

import org.example.controller.generative.ChatGPTAdventure
import org.example.controller.generative.setup.SetupHelper
import org.example.model.Environment
import org.example.model.settings.Settings
import java.util.*

class IOTest

fun main(args: Array<String>) {
    SetupHelper().startOlama()

    val scanner = Scanner(System.`in`)
    val adventure = ChatGPTAdventure(Settings, Environment())
    while (true) {
        val line = scanner.nextLine()
        val response = adventure.generateStoryIndependentStuff(line)
        System.out.println(response)
    }
}
