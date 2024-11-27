package de.hartz.software.protegard.model.settings

object Settings {

    const val DEFAULT_LANGUAGE = "german" // "english"

    var useLLMs = true

    var useEffectsAndUi = true
    var translate = true
    var language: String = DEFAULT_LANGUAGE
}