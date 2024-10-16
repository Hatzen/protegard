package controller.generative

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.TokenStream

interface ModelCommunication {

    @SystemMessage(
        """
            You generate texts for an text based point adventure game.
            Which is playing in 1920. You are not the narrator and just provide useful texts generated based on the input.
            You must be carful what you generate has to fit in the storyline so 
            """ // TODO: Load chapters from files
    )
    fun chatWithModel(message: String): TokenStream
}