package de.hartz.software.protegard.controller.generative.translation

import dev.langchain4j.service.UserMessage


interface TranslationModelCommunication {
    fun translateTextToLanguage(
        @UserMessage message: String
    ): String
    /*@SystemMessage(
        """
          YOU JUST SIMPLY TRANSLATE THE PROMPT from language {{languageFrom}} to {{languageTo}}. NOTHING MORE.
      """
    )
    fun translateTextToLanguage(
        @UserMessage message: String,
        @V("languageTo") languageTo: String,
        @V("languageFrom") languageFrom: String
    ): String*/
    // TODO: Maybe better use Tools to keep and store the language to avoid false positives.
    // TODO: Or in general use tools to use deepl to get more proper translations?
}