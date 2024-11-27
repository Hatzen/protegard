package de.hartz.software.protegard.controller.generative.content

import de.hartz.software.protegard.controller.generative.content.GenerativeService.Companion.StoryBasedMemoryId
import de.hartz.software.protegard.controller.generative.content.GenerativeService.Companion.StoryIndependendMemoryId
import de.hartz.software.protegard.controller.generative.content.GenerativeService.Companion.TechniclaMemoryId
import dev.langchain4j.service.*


interface ContentGenerationModelCommunication {
    @SystemMessage(
        """
        # CONTEXT #
        I want you to make my adventure game more immersive by adding meaningless but immersive description of the environment the gamer is currently in.
    
        # OBJECTIVE #
        I will pass you prompts with the necessary informations, which entities are currently available for the gamer and you create a realistic description. The message is what you 90% should rely on. The other stuff i tell you here is just for priming your context.
        
        # STYLE #
        All entities which are not listed in the user prompt are BLACKLISTED.
         You answer always in language {{language}}, except entities enclosed with '' or "" must be kept the exact way they were passed. THIS IS MANDATORY.
        You do not use any markdown formatting or enumerations, just plain blocktext.
        
        # TONE #
        Diffuse, but descriptive on details which are irrelevant but highly probably by the given entities and the chapter description.
        
        # AUDIENCE #
        The gamer who is playing my game and following my instructions. You are not allowed to give him instructions or choices.
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
            You do not use any markdown formatting.
             You do not interact with the user at all. 
            You only do what it asked in the prompts.
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

    fun getTechnicalResponse(
        @UserMessage message: String,
        @MemoryId id: Int = TechniclaMemoryId
    ): TokenStream
}