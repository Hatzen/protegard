package org.example.controller.generative

import controller.generative.ChatService
import org.example.controller.generative.setup.SetupHelper
import org.example.model.common.Environment
import org.example.model.settings.Settings
import java.util.concurrent.CompletableFuture

class ChatGPTAdventure(private val settings: Settings, private val environment: Environment) {

    companion object {
        const val API_URL: String = "http://localhost:11434"
        const val MODEL = "llama3.2"
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
        chapter: String = environment.currentChapter.getChaptersUntilThis().joinToString { it.chapterContent }
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
        languageFrom: String = "detect source language"
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