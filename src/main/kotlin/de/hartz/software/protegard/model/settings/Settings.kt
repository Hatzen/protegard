package de.hartz.software.protegard.model.settings

object Settings {

    const val DEFAULT_LANGUAGE = "german" // "english"

    var useLLMs = true

    // Turn off for testing to get texts instantly.
    var useEffectsAndUi = false // TODO: currently Enter> is lead by text.
    var translate = false // TODO the translationmodel just prompts random shit. single prompt show useful stuff.
    var language: String = DEFAULT_LANGUAGE
}