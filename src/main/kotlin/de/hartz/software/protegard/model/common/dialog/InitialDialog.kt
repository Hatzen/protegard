package de.hartz.software.protegard.model.common.dialog

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.scenario.Characters

// TODO: Is this useful?
//   Dialog where we could add some generated preamble like "hi, good day, whats the matter" for start and then go to further dialog options which might change dynamically.
open class InitialDialog(
    text: String,
    answers: MutableList<Dialog>? = null,
    onlyOnce: Boolean = false,
    target: Character = Characters.MAIN_CHARACTER,
    source: Character = Characters.MAIN_CHARACTER,
    precondition: () -> Boolean = { true },
     callback: () -> Unit = { }
): Dialog(text, answers, onlyOnce, target, source, precondition, callback)