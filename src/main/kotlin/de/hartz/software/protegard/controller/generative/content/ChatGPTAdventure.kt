package de.hartz.software.protegard.controller.generative.content

import de.hartz.software.protegard.controller.generative.setup.SetupHelper
import de.hartz.software.protegard.model.common.Gamestate
import de.hartz.software.protegard.model.settings.Settings
import java.util.concurrent.CompletableFuture

class ChatGPTAdventure(private val settings: Settings, private val gamestate: Gamestate) {

    companion object {
        const val API_URL: String = "http://localhost:11434"

        // "gemma2:27b" similar results, less creative.
        // TODO: If we want RAG but probably not needed so far?
        // https://ollama.com/library/nemotron-mini
        const val MODEL = "llama3.2"
        const val STATIC_NO_LLM_ANSWER = "The cake is a lie.."
    }

    private val chatService: GenerativeService?

    init {
        chatService = if (SetupHelper.isOllamaInstalled) {
            GenerativeService(API_URL, MODEL)
        } else {
            null
        }
    }

    fun getNarratorBasedContent(
        message: String,
        language: String = settings.language,
        chapter: String = gamestate.currentChapter.getChaptersUntilThis().joinToString { it.chapterContent }
    ): String {
        return getDynamicResponse { chatService!!.getNarratorBasedContent(message, language, chapter) }
    }

    fun generateStoryIndependentStuff(
        message: String,
        language: String = settings.language,
    ): String {
        return getDynamicResponse { chatService!!.generateStoryIndependentStuff(message, language) }
    }

    fun isCorrectAnswerToPreviousGeneratedQuestion(
        message: String,
    ): Boolean {
        if (!settings.useLLMs) {
            return true
        }
        return chatService!!.isCorrectAnswerToPreviousGeneratedQuestion(message)
    }

    fun getTechnicalResponse(
        message: String
    ): String {
        return getDynamicResponse { chatService!!.getTechnicalResponse(message) }
    }


    private fun getDynamicResponse(supp: () -> CompletableFuture<String>): String {
        if (!settings.useLLMs) {
            return STATIC_NO_LLM_ANSWER
        }
        val response = supp().join()
        return response
    }
}