package de.hartz.software.protegard.model.settings

object Settings {

    const val DEFAULT_LANGUAGE = "english"

    var useLLMs = true

    //
    // TODO: Currently leading to issues, parallel input and output leading to infinte wrong input..
    var useEffectsAndUi = false
    var translate = false
    var language: String = DEFAULT_LANGUAGE
}