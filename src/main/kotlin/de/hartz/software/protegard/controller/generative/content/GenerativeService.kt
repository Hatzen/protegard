package de.hartz.software.protegard.controller.generative.content

import de.hartz.software.protegard.controller.LoggerUtil.logger
import dev.langchain4j.data.message.AiMessage
import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.model.chat.StreamingChatLanguageModel
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.model.ollama.OllamaStreamingChatModel
import dev.langchain4j.model.output.Response
import dev.langchain4j.service.AiServices
import dev.langchain4j.service.TokenStream
import java.time.Duration
import java.util.concurrent.CompletableFuture

// https://tpbabparn.medium.com/java-ollama-unlock-capability-of-generative-ai-to-java-developer-with-langchain4j-model-on-c814f97d9676
class GenerativeService {

    companion object {
        const val StoryBasedMemoryId = 1
        const val StoryIndependendMemoryId = 2
        const val TranslationMemoryId = 3
        const val TechniclaMemoryId = 4
    }

    val languageModel: StreamingChatLanguageModel
    val assistant: ContentGenerationModelCommunication

    constructor(modelUrl: String, modelName: String) {
        this.languageModel = connectModel(modelUrl, modelName)
        val languageModel2 = connectModelProcedural(modelUrl, modelName)
        // Memorize for 100 messages continuously
        this.assistant = AiServices.builder(ContentGenerationModelCommunication::class.java)
            // Alternative of .chatLanguageModel() which support streaming response
            .chatLanguageModel(languageModel2)
            .streamingChatLanguageModel(this.languageModel)
            .chatMemoryProvider { memoryId ->
                val messageCount = when (memoryId) {
                    StoryBasedMemoryId -> 1000
                    StoryIndependendMemoryId -> 10
                    // IMPORTANT NOTE: We need at least 2 message count for SYSTEM Message AND User message, otherwise returns null response.
                    TranslationMemoryId -> 2
                    TechniclaMemoryId -> 3
                    else -> throw RuntimeException("memory id not supported.")
                }
                MessageWindowChatMemory.withMaxMessages(messageCount)
            }
            .build()
    }

    private fun connectModel(modelUrl: String, modelName: String): StreamingChatLanguageModel {
        return OllamaStreamingChatModel.builder()
            .baseUrl(modelUrl)
            .modelName(modelName)
            .timeout(Duration.ofSeconds(1000))
            .temperature(0.3)
            .build()
    }

    // Must be used for boolean answers and tools etc.
    private fun connectModelProcedural(modelUrl: String, modelName: String): OllamaChatModel {
        return OllamaChatModel.builder()
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

    fun getTechnicalResponse(message: String): CompletableFuture<String> {
        logRequest("getTechnicalResponse", message)
        return ask { assistant.getTechnicalResponse(message) }
    }

    private fun logRequest(name: String, message: String) {
        logger.debug { "-------------------Request, $name----------------------: $message" }
    }
}