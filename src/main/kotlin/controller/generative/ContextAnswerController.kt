package org.example.controller.generative

import dev.langchain4j.service.MemoryId
import org.example.controller.generative.ChatGPTAdventure.Companion.STATIC_NO_LLM_ANSWER

object ContextAnswerController {

    fun getBookContentForTopic(@MemoryId topic: String): String {
        return getRandomAnswerForSeed(
            """
         Write content for a book page that contains real scientific or historical data that is not commonly known,
          and provides information for $topic. Be aware that you need to generate a question for this content in the next step,
           that anyone can answer only by this content and which where you can verify a fulltext answer to a simple true or false.
            """.trimIndent()
        )
    }

    fun getQuestionForTopic(@MemoryId topic: String): String {
        return getRandomAnswerForSeed(
            """
         Like told before give me a single question for the topic $topic       
            """.trimIndent()
        )
    }

    fun isValidAnswerForTopic(answer: String): Boolean {
        val matched = "true"
        val notMached = "false"
        val evaluated = getRandomAnswerForSeed(
            """
         Answer only exactly "$matched" or "$notMached" if the answer for your previous generated question is correct. The Answer is "$answer"
            """.trimIndent()
        ).lowercase()
        if (!listOf(matched, notMached, STATIC_NO_LLM_ANSWER).contains(evaluated)) {
            throw RuntimeException("evaluated $evaluated is not a valid response")
        }
        return evaluated == STATIC_NO_LLM_ANSWER || evaluated == matched
    }

    private fun getRandomAnswerForSeed(seed: String): String {
        return ChatGPTAdventure().getDynamicResponse(seed)
    }

}