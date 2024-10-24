package controller.generative

import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.model.chat.StreamingChatLanguageModel
import dev.langchain4j.model.ollama.OllamaStreamingChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.service.TokenStream
import java.time.Duration
import java.util.concurrent.CompletableFuture

// https://tpbabparn.medium.com/java-ollama-unlock-capability-of-generative-ai-to-java-developer-with-langchain4j-model-on-c814f97d9676
class ChatService : UserStreamCommunication, ModelCommunication {

    val languageModel: StreamingChatLanguageModel
    val assistant: ModelCommunication

    constructor(modelUrl: String, modelName: String) {
        this.languageModel = connectModel(modelUrl, modelName)
        // TODO: We should set some context for "ever" and avoid manipulating the memory for dummy responses.
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
            .timeout(Duration.ofHours(1))
            .temperature(1.0)
            .build()
    }

    // Could you give me the way to exercise for office worker, please?
    // Based on previous answer, What if I don't have much place outside?
    override fun ask(userPrompt: String): CompletableFuture<String> {
        val tokenStream = chatWithModel(userPrompt)
        val future = CompletableFuture<String>()

        /*
        // use streamed response or show loading progress..
        tokenStream.onNext(System.out::print)
            .onComplete {
                System.out.println()
                future.complete(null)
            }
            .onError(Throwable::printStackTrace)
            .start()
         */
        tokenStream
            .onNext(System.out::print)
            .onComplete {
                val response = it.content().text()
                future.complete(response)
            }
            .onError(Throwable::printStackTrace)
            .start()
        return future
    }

    override fun chatWithModel(message: String): TokenStream {
        return assistant.chatWithModel(message)
    }
}