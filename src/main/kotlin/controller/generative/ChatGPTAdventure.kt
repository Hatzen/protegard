package org.example.controller.generative

import controller.generative.ChatService

class ChatGPTAdventure {
    /**
     *
     * @param context
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getDynamicResponse(context: String): String {
        if (!USE_LLMS) {
            return "The cake is a lie.."
        }
        val response = getGPTResponse(context)
        return response
    }

    companion object {
        const val API_URL: String = "http://localhost:11434"

        // TODO: set by user input or wether olama already installed
        private val USE_LLMS: Boolean = true

        @Throws(Exception::class)
        private fun getGPTResponse(prompt: String): String {
            val chatService = ChatService(API_URL, "llama3.2")
            return chatService.ask(prompt).join()
        }
    }
}