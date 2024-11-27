package de.hartz.software.protegard.controller.generative.translation

import de.hartz.software.protegard.controller.LoggerUtil.logger
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.service.AiServices
import java.time.Duration

class TranslationService {

    val assistant: TranslationModelCommunication

    constructor(modelUrl: String, modelName: String) {
        val languageModel = connectModelProcedural(modelUrl, modelName)
        this.assistant = AiServices.builder(TranslationModelCommunication::class.java)
            .chatLanguageModel(languageModel)
            .build()
    }

    private fun connectModelProcedural(modelUrl: String, modelName: String): OllamaChatModel {
        return OllamaChatModel.builder()
            .baseUrl(modelUrl)
            .modelName(modelName)
            .timeout(Duration.ofSeconds(1000))
            // 0.3 be more determistic dont fantasise
            .temperature(0.3)
            // Allow to repeat the same words again and again
            .repeatPenalty(0.0)
            .build()
    }

    fun translate(message: String, languageTo: String, languageFrom: String): String {
        logRequest("translate", message)
        return assistant.translateTextToLanguage(message, languageTo, languageFrom)
    }

    private fun logRequest(name: String, message: String) {
        logger.debug { "-------------------Request, $name----------------------: $message" }
    }
}