package de.hartz.software.protegard.controller.generative

import dev.langchain4j.service.MemoryId

class ContextAnswerController(private val chatGPTAdventure: ChatGPTAdventure) {

    fun getBookContentForTopic(@MemoryId topic: String): String {
        return chatGPTAdventure.generateStoryIndependentStuff(
            """
         Write content for a book page that contains real scientific or historical data that is not commonly known,
          and provides information for $topic. Be aware that you need to generate a question for this content in the next step,
           that anyone can answer only by this content and which where you can verify a fulltext answer to a simple true or false.
            """.trimIndent()
        )
    }

    fun getQuestionForTopic(@MemoryId topic: String): String {
        return chatGPTAdventure.generateStoryIndependentStuff(
            """
         Like told before give me a single question for the topic $topic       
            """.trimIndent()
        )
    }

    fun isValidAnswerForTopic(answer: String): Boolean {
        val matched = "true"
        val notMached = "false"
        val evaluated = chatGPTAdventure.isCorrectAnswerToPreviousGeneratedQuestion(
            """
         Answer only exactly "$matched" or "$notMached" if the answer for your previous generated question is correct. The Answer is "$answer"
            """.trimIndent()
        )
        return evaluated
    }

}