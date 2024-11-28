package de.hartz.software.protegard.controller.generative.translation

import de.hartz.software.protegard.controller.generative.content.ChatGPTAdventure.Companion.API_URL
import de.hartz.software.protegard.controller.generative.setup.SetupHelper
import de.hartz.software.protegard.model.settings.Settings

class TranslationGPT(private val settings: Settings) {

    companion object {
        // https://ollama.com/thinkverse/towerinstruct
        const val MODEL = "thinkverse/towerinstruct"
    }

    private val chatService: TranslationService?

    init {
        chatService = if (SetupHelper.isOllamaInstalled) {
            TranslationService(API_URL, MODEL)
        } else {
            null
        }
    }

    fun translate(
        message: String,
        languageTo: String = settings.language,
        languageFrom: String = "english" // or maybe "detect source language"
    ): String {
        if (!settings.useLLMs || !settings.translate) {
            return message
        }
        return chatService!!.translate(message, languageTo, languageFrom)
    }

}