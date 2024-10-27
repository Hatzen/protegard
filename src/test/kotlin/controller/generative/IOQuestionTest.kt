package controller.generative

import org.example.controller.generative.ContextAnswerController
import org.example.controller.generative.setup.SetupHelper
import java.util.*


fun main(args: Array<String>) {
    SetupHelper().startOlama()

    val scanner = Scanner(System.`in`)
    val book = ContextAnswerController.getBookContentForTopic("Ägypten")
    println(book)
    val question = ContextAnswerController.getQuestionForTopic("Ägypten")
    println(question)
    val answer = scanner.nextLine()

    do {
        val valid = ContextAnswerController.isValidAnswerForTopic(answer)
        assert(
            valid
        )
    } while (!valid)
}
