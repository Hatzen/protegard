package controller.generative

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.TokenStream
import dev.langchain4j.service.V

interface ModelCommunication {

    @SystemMessage(
        """
            You answer always in language {{language}}.
            You generate texts for an text based point adventure game which is playing in 1920.
            You are not the narrator and just provide useful texts generated based on the input.
            You must be carful what you generate has to fit in the storyline.
            The previous chapters story is:
             
             {{chapter}}
            """
    )
    fun getNarratorBasedContent(
        message: String,
        @V("language") language: String,
        @V("chapter") chapter: String
    ): TokenStream


    @SystemMessage(
        """
            You answer always in language {{language}}.    
        """
    )
    fun generateStoryIndependentStuff(message: String, @V("language") language: String): TokenStream

    @SystemMessage(
        """
            You are the perfect translator you translate any input in a manner that native and fluent speakers would use to translate even 
            Translate the input from {{languageFrom}} to {{languageTo}}.    
        """
    )
    fun translate(
        message: String,
        @V("languageTo") languageTo: String,
        @V("languageFrom") languageFrom: String
    ): TokenStream
}