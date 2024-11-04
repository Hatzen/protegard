package controller.generative

import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.model.chat.StreamingChatLanguageModel
import dev.langchain4j.model.ollama.OllamaChatModel
import dev.langchain4j.model.ollama.OllamaStreamingChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.service.TokenStream
import java.time.Duration
import java.util.concurrent.CompletableFuture

// https://tpbabparn.medium.com/java-ollama-unlock-capability-of-generative-ai-to-java-developer-with-langchain4j-model-on-c814f97d9676
class ChatService {

    companion object {
        const val StoryBasedMemoryId = 1
        const val StoryIndependendMemoryId = 2
        const val TranslationMemoryId = 3
        const val TechniclaMemoryId = 4
    }

    val languageModel: StreamingChatLanguageModel
    val assistant: ModelCommunication

    constructor(modelUrl: String, modelName: String) {
        this.languageModel = connectModel(modelUrl, modelName)
        val languageModel2 = connectModelProcedural(modelUrl, modelName)
        // Memorize for 100 messages continuously
        this.assistant = AiServices.builder(ModelCommunication::class.java)
            // Alternative of .chatLanguageModel() which support streaming response
            .chatLanguageModel(languageModel2)
            .streamingChatLanguageModel(this.languageModel)
            .chatMemoryProvider { memoryId ->
                val messageCount = when (memoryId) {
                    StoryBasedMemoryId -> 1000
                    StoryIndependendMemoryId -> 10
                    TranslationMemoryId -> 1
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
            // TODO: Evaluate proper values. Maybe use different instances for different usecases.
            // .temperature(0.5)
            // .topP(1.0)
            // .topK(1000)
            // .numPredict(1000)
            // .numCtx(1000)
            .build()
    }

    private fun connectModelProcedural(modelUrl: String, modelName: String): OllamaChatModel {
        return OllamaChatModel.builder()
            .baseUrl(modelUrl)
            .modelName(modelName)
            .timeout(Duration.ofSeconds(1000))
            .build()
    }

    private fun ask(supp: () -> TokenStream): CompletableFuture<String> {
        val tokenStream = supp()
        val future = CompletableFuture<String>()

        tokenStream
            //.onNext(System.out::println)
            .onNext {}
            .onComplete {
                val response = it.content().text()
                future.complete(response)
            }
            .onError(Throwable::printStackTrace)
            .start()
        return future
    }

    fun getNarratorBasedContent(
        message: String,
        language: String,
        chapter: String
    ): CompletableFuture<String> {
        return ask { assistant.getNarratorBasedContent(message, language, chapter) }
    }

    fun generateStoryIndependentStuff(
        message: String,
        language: String
    ): CompletableFuture<String> {
        return ask { assistant.generateStoryIndependentStuff(message, language) }
    }

    fun isPositive(
        message: String
    ): Boolean {
        return assistant.isPositive(message)
    }

    fun translate(message: String, languageTo: String, languageFrom: String): CompletableFuture<String> {
        return ask { assistant.translate(message, languageTo, languageFrom) }
    }

    fun getTechnicalResponse(message: String): CompletableFuture<String> {
        return ask { assistant.getTechnicalResponse(message) }
    }
}