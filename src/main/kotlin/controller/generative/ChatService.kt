package controller.generative

import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.model.chat.StreamingChatLanguageModel
import dev.langchain4j.model.ollama.OllamaStreamingChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.service.TokenStream
import java.time.Duration
import java.util.concurrent.CompletableFuture

// https://tpbabparn.medium.com/java-ollama-unlock-capability-of-generative-ai-to-java-developer-with-langchain4j-model-on-c814f97d9676
class ChatService {

    val languageModel: StreamingChatLanguageModel
    val assistant: ModelCommunication

    constructor(modelUrl: String, modelName: String) {
        this.languageModel = connectModel(modelUrl, modelName)
        // Memorize for 100 messages continuously
        val chatMemory = MessageWindowChatMemory.withMaxMessages(100)
        this.assistant = AiServices.builder(ModelCommunication::class.java)
            // Alternative of .chatLanguageModel() which support streaming response
            .streamingChatLanguageModel(this.languageModel)
            .chatMemory(chatMemory)
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

    fun translate(message: String, languageTo: String, languageFrom: String): CompletableFuture<String> {
        return ask { assistant.translate(message, languageTo, languageFrom) }
    }
}