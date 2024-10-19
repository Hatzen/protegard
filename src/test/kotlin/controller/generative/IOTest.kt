package controller.generative

import org.example.controller.generative.ChatGPTAdventure
import org.example.controller.generative.setup.SetupHelper
import java.util.*

class IOTest

fun main(args: Array<String>) {
    SetupHelper().startOlama()

    val scanner = Scanner(System.`in`)
    val adventure = ChatGPTAdventure()
    while (true) {
        val line = scanner.nextLine()
        val response = adventure.getDynamicResponse(line)
        System.out.println(response)
    }
}