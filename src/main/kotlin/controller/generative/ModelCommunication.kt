package controller.generative

import controller.generative.ChatService.Companion.StoryBasedMemoryId
import controller.generative.ChatService.Companion.StoryIndependendMemoryId
import controller.generative.ChatService.Companion.TechniclaMemoryId
import controller.generative.ChatService.Companion.TranslationMemoryId
import dev.langchain4j.service.*


// TODO: Edit prompts to remove formatting like ** ** for headline
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
        @UserMessage message: String,
        @V("language") language: String,
        @V("chapter") chapter: String,
        @MemoryId id: Int = StoryBasedMemoryId
    ): TokenStream


    @SystemMessage(
        """
            You answer always in language {{language}}.    
        """
    )
    fun generateStoryIndependentStuff(
        @UserMessage message: String,
        @V("language") language: String,
        @MemoryId id: Int = StoryIndependendMemoryId
    ): TokenStream

    @UserMessage(
        """
        {{message}}.
        Is that the correct answer to your previous told question?
        """
    )
    fun isCorrectAnswerToPreviousGeneratedQuestion(
        @V("message")
        message: String,
        @MemoryId id: Int = StoryIndependendMemoryId
    ): Boolean


    @SystemMessage(
        """
        You are the perfect translator you translate any input in a manner that native and fluent speakers would use to translate even 
        Translate the input from {{languageFrom}} to {{languageTo}}.    
    """
    )
    fun translate(
        @UserMessage message: String,
        @V("languageTo") languageTo: String,
        @V("languageFrom") languageFrom: String,
        // TODO: Maybe better use Tools to keep and store the language to avoid false positives.
        // TODO: Or in general use tools to use deepl to get more proper translations?
        @MemoryId id: Int = TranslationMemoryId
    ): TokenStream

    fun getTechnicalResponse(
        @UserMessage message: String,
        @MemoryId id: Int = TechniclaMemoryId
    ): TokenStream
}