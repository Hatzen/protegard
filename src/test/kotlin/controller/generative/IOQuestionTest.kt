package controller.generative

import de.hartz.software.protegard.controller.generative.ChatGPTAdventure
import de.hartz.software.protegard.controller.generative.ContextAnswerController
import de.hartz.software.protegard.controller.generative.setup.SetupHelper
import de.hartz.software.protegard.model.common.Gamestate
import de.hartz.software.protegard.model.settings.Settings
import java.util.*


fun main(args: Array<String>) {
    val objectUnderTest = ContextAnswerController(ChatGPTAdventure(Settings, Gamestate()))
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
