package de.hartz.software.protegard.model.common

import de.hartz.software.protegard.model.scenario.Characters

open class Dialog(
    val text: String,
    val answers: MutableList<Dialog>? = null,
    var onlyOnce: Boolean = false,
    var target: Character = Characters.MAIN_CHARACTER,
    var source: Character = Characters.MAIN_CHARACTER,
    var precondition: () -> Boolean = { true },
    // Callback to have an action after an dialoge.
    var callback: () -> Unit = { }
) {
    init {
        assert(target != source)
    }

    constructor(
        text: String,
        answers: Dialog,
        onlyOnce: Boolean = false,
        target: Character = Characters.MAIN_CHARACTER,
        source: Character = Characters.MAIN_CHARACTER,
        precondition: () -> Boolean = { true },
        callback: () -> Unit = { }
    ) : this(text, mutableListOf(answers), onlyOnce, target, source, precondition, callback)
}
