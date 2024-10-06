package org.example.model

import jdk.jfr.Percentage
import org.example.model.interfaces.Identifieable
import org.example.model.interfaces.Interactable
import org.example.model.scenario.Characters

open class Dialog(
    val text: String,
    val answer: List<Dialog>? = null,
    var onlyOnce: Boolean = false,
    var target: Character = Characters.MAIN_CHARACTER,
    var precondition: () -> Boolean = { true }
) {

}
