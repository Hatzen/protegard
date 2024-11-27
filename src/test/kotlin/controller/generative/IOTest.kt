package controller.generative

import de.hartz.software.protegard.controller.generative.content.ChatGPTAdventure
import de.hartz.software.protegard.controller.generative.setup.SetupHelper
import de.hartz.software.protegard.model.common.Gamestate
import de.hartz.software.protegard.model.settings.Settings
import java.util.*

class IOTest

fun main(args: Array<String>) {
    SetupHelper().startOlama()

    val scanner = Scanner(System.`in`)
    val adventure = ChatGPTAdventure(Settings, Gamestate())
    while (true) {
        val line = scanner.nextLine()
        val response = adventure.generateStoryIndependentStuff(line)
        System.out.println(response)
    }
}
