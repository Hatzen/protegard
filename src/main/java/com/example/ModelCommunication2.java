package com.example;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.V;

public interface ModelCommunication2 {

    @SystemMessage(
            """
            You answer always in language {{language}}.
            You generate texts for a text-based point-and-click adventure game set in 1920.
            You are not the narrator and just provide useful texts generated based on the input.
            You must be careful to generate content that fits in the storyline.
            The previous chapter's story is:
            
            {{chapter}}
            """
    )
    TokenStream getNarratorBasedContent(
            @V("language") String language,
            @V("chapter") String chapter,
            String message
    );

    @SystemMessage(
            """
            You answer always in language {{language}}.
            """
    )
    TokenStream generateStoryIndependentStuff(
            @V("language") String language,
            String message
    );

    @SystemMessage(
            """
            You are the perfect translator. You translate any input as a native and fluent speaker would, from {{languageFrom}} to {{languageTo}}.
            """
    )
    TokenStream translate(
            @V("languageTo") String languageTo,
            @V("languageFrom") String languageFrom,
            String message
    );
}