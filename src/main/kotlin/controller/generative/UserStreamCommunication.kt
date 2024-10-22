package controller.generative

import java.util.concurrent.CompletableFuture

interface UserStreamCommunication {

    fun ask(userPrompt: String): CompletableFuture<String>

}