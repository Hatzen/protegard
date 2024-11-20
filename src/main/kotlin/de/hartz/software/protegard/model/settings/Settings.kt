package de.hartz.software.protegard.model.settings

object Settings {

    const val DEFAULT_LANGUAGE = "english"

    var useLLMs = true

    //
    var useEffectsAndUi = true
    var translate = false
    var language: String = DEFAULT_LANGUAGE
}