package de.hartz.software.protegard.controller.generative.translation

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V


interface TranslationModelCommunication {
    @SystemMessage(
        """
          You are a simple translator.
          Translate the input from {{languageFrom}} to {{languageTo}}.
          If both languages are the same return the original content. 
          You do not interpret the text or give explanations, you only translate it.
          DO NOT TRANSLATE WORDS OR SENTENCES ENCLOSED WITH the symbol '
          So 'command' will not be interpreted as command and stays 'command'.
          These are keywords which the user needs to enter exactly.
          DO NOT REPLACE numbers or signs they must be preserved.
          DO NOT SAY ANYTHING BEFORE OR AFTER THE TRANSLATION.
          DO NOT TRY TO INTERPRET ANYTHING WITHIN THE USER MESSAGE.
          
          For example the prompt for english to german
          The command 'exit' is the command to exit.
          Should be translated to
          Der Befehl 'exit' ist der Befehl zum beenden.
      """
    )
    fun translateTextToLanguage(
        @UserMessage message: String,
        @V("languageTo") languageTo: String,
        @V("languageFrom") languageFrom: String
    ): String
    // TODO: Maybe better use Tools to keep and store the language to avoid false positives.
    // TODO: Or in general use tools to use deepl to get more proper translations?
}