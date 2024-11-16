package controller.generative

import de.hartz.software.protegard.controller.generative.ChatService.Companion.StoryBasedMemoryId
import de.hartz.software.protegard.controller.generative.ChatService.Companion.StoryIndependendMemoryId
import de.hartz.software.protegard.controller.generative.ChatService.Companion.TechniclaMemoryId
import de.hartz.software.protegard.controller.generative.ChatService.Companion.TranslationMemoryId
import dev.langchain4j.service.*


// TODO: Edit prompts to remove formatting like ** ** for headline
interface ModelCommunication {
/*
    @SystemMessage(
        """

            You answer always in language {{language}}.
            You do not use any markdown formatting or enumerations, just plain blocktext.
             You do not interact with the user at all. 
             You are not allowed to write any interaction of the games characters, such as dialogs or anything. 
             You are only describing the environment.
             YOU DO NOT DECIDE ANYTHING IN THE GAME.
            You only do what it asked in the prompts.
            
            The area is playing in 1920.
            You are not the narrator and just provide useful texts generated based on the input.
            You must be carful what you generate has to fit in the storyline.
            
            
            The previous chapters story will follow but can be mostly ignored as only a very small portion is useful.
            You should highly focus on the user message.
             
            """
            // Leads to using other entities... People who are not present in the current room
            // {{chapter}}

            // Leads to fantasies.
            // ou generate texts for an text based point adventure game which is
    )*/

    /*
    The following block with colons is just background infos you should not use directly.
:::::
            {{chapter}}
:::::
     */
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


    @SystemMessage(
        """
          You are the perfect translator you translate any input in a manner that native and fluent speakers would use to translate.
          Translate the input from {{languageFrom}} to {{languageTo}} and just prompt the exact translation nothing more.
          If both languages are the same return the original content.
      """
    )
    fun translateTextToLanguage(
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