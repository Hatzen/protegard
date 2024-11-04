package controller.generative

import org.example.controller.generative.ChatGPTAdventure
import org.example.controller.generative.ContextAnswerController
import org.example.controller.generative.setup.SetupHelper
import org.example.model.Environment
import org.example.model.settings.Settings
import java.util.*


fun main(args: Array<String>) {
    val objectUnderTest = ContextAnswerController(ChatGPTAdventure(Settings, Environment()))
    SetupHelper().startOlama()

    val scanner = Scanner(System.`in`)
    val book = objectUnderTest.getBookContentForTopic("Ägypten")
    println(book)
    val question = objectUnderTest.getQuestionForTopic("Ägypten")
    println(question)

    do {
        val answer = scanner.nextLine()
        val valid = objectUnderTest.isValidAnswerForTopic(answer)
        // assert(
        //     valid
        // )
        if (valid) {
            println("Answer correct, test passed")
        } else {
            println("Answer incorrect, try again")
        }
    } while (!valid)
}
