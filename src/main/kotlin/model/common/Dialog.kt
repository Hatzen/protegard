package org.example.model.common

import org.example.model.scenario.Characters

open class Dialog(
    val text: String,
    val answers: MutableList<Dialog>? = null,
    var onlyOnce: Boolean = false,
    var target: Character = Characters.MAIN_CHARACTER,
    var source: Character = Characters.MAIN_CHARACTER,
    var precondition: () -> Boolean = { true }
)
