package de.hartz.software.protegard.controller.generative

import de.hartz.software.protegard.controller.generative.setup.SetupHelper
import de.hartz.software.protegard.model.common.Gamestate
import de.hartz.software.protegard.model.settings.Settings
import java.util.concurrent.CompletableFuture

class ChatGPTAdventure(private val settings: Settings, private val gamestate: Gamestate) {

    companion object {
        const val API_URL: String = "http://localhost:11434"
        // very bad with translation "llama3.2"
        // takes all ram and takes 5 min for simple tasks. "gemma2:27b"
        // Wont stop adding preamble and takes long to answer.. "gemma2"
        const val MODEL =  "llama3.2" // "gemma2" // "llama3.2" // "llama3.2" "gemma2:27b"
        const val STATIC_NO_LLM_ANSWER = "The cake is a lie.."
    }

    private val chatService: ChatService?

    init {
        chatService = if (SetupHelper.isOllamaInstalled) {
            ChatService(API_URL, MODEL)
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

    fun translate(
        message: String,
        languageTo: String = settings.language,
        languageFrom: String = "english" // TODO: This would be nice but does lead to many errors "detect source language"
    ): String {
        return getDynamicResponse { chatService!!.translate(message, languageTo, languageFrom) }
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