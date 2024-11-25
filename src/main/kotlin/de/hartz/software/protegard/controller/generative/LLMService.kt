package de.hartz.software.protegard.controller.generative

import controller.generative.ModelCommunication
import de.hartz.software.protegard.controller.LoggerUtil.logger
import dev.langchain4j.data.message.AiMessage
import dev.langchain4j.model.language.LanguageModel
import dev.langchain4j.model.language.StreamingLanguageModel
import dev.langchain4j.model.ollama.OllamaLanguageModel
import dev.langchain4j.model.ollama.OllamaStreamingLanguageModel
import dev.langchain4j.model.output.Response
import dev.langchain4j.service.TokenStream
import java.time.Duration
import java.util.concurrent.CompletableFuture

// https://tpbabparn.medium.com/java-ollama-unlock-capability-of-generative-ai-to-java-developer-with-langchain4j-model-on-c814f97d9676
class LLMService {

    val languageModel: StreamingLanguageModel
    val languageModel2: LanguageModel

    val assistant: ModelCommunication

    constructor(modelUrl: String, modelName: String) {
        this.languageModel = connectModel(modelUrl, modelName)
        this.languageModel2 = connectModelProcedural(modelUrl, modelName)
        assistant = SimpleModelCommunication(languageModel, languageModel2)
    }

    private fun connectModel(modelUrl: String, modelName: String): OllamaStreamingLanguageModel {
        return OllamaStreamingLanguageModel.builder()
            .baseUrl(modelUrl)
            .modelName(modelName)
            .timeout(Duration.ofSeconds(1000))
            .temperature(0.3)
            .build()
    }

    // Must be used for boolean answers and tools etc.
    private fun connectModelProcedural(modelUrl: String, modelName: String): OllamaLanguageModel {
        return OllamaLanguageModel.builder()
            .baseUrl(modelUrl)
            .modelName(modelName)
            .timeout(Duration.ofSeconds(1000))
            // TODO: Evaluate proper values. Maybe use different instances for different usecases.
            // 0.3 be more determistic dont fantasise
            .temperature(0.3)
            // Tokens propability must sum up to 0.65
            .topP(0.65)
            // Use up to 30 good tokens. More tokens more variance
            .topK(30)
            // Allow to repeat the same words again and again
            .repeatPenalty(0.0)
            // Limit context tokens to be used for answers.
            .numCtx(1000)
            // Limit text length to 1000 token
            // TODO: Leading to content being null and crashing for some reason..
            // .numPredict(1000)
            .build()
    }

    private fun ask(supp: () -> TokenStream): CompletableFuture<String> {
        val tokenStream = supp()
        val future = CompletableFuture<String>()

        tokenStream
            //.onNext(System.out::println)
            .onNext {}
            .onComplete { onCompleteCallback(future, it) }
            .onError(Throwable::printStackTrace)
            .start()
        return future
    }

    private fun onCompleteCallback(future: CompletableFuture<String>, it: Response<AiMessage>) {
        val response = it.content().text()
        logger.debug { "-------------------Reponse----------------------: $response" }
        future.complete(response)
    }

    fun getNarratorBasedContent(
        message: String,
        language: String,
        chapter: String
    ): CompletableFuture<String> {
        logRequest("getNarratorBasedContent", message)
        return ask { assistant.getNarratorBasedContent(message, language, chapter) }
    }

    fun generateStoryIndependentStuff(
        message: String,
        language: String
    ): CompletableFuture<String> {
        logRequest("generateStoryIndependentStuff", message)
        return ask { assistant.generateStoryIndependentStuff(message, language) }
    }

    fun isCorrectAnswerToPreviousGeneratedQuestion(
        message: String
    ): Boolean {
        logRequest("isCorrectAnswerToPreviousGeneratedQuestion", message)
        return assistant.isCorrectAnswerToPreviousGeneratedQuestion(message)
    }

    fun translate(message: String, languageTo: String, languageFrom: String): CompletableFuture<String> {
        logRequest("translate", message)
        return ask { assistant.translateTextToLanguage(message, languageTo, languageFrom) }
    }

    fun getTechnicalResponse(message: String): CompletableFuture<String> {
        logRequest("getTechnicalResponse", message)
        return ask { assistant.getTechnicalResponse(message) }
    }

    private fun logRequest(name: String, message: String) {
        logger.debug { "-------------------Request, $name----------------------: $message" }
    }
}