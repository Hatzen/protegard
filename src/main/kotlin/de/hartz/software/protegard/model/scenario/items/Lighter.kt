package de.hartz.software.protegard.model.scenario.items

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Item
import de.hartz.software.protegard.model.scenario.Characters

class Lighter : Item("Lighter") {

    var isEmpty = false
    var isBurning = false


    override fun interact() {
        isBurning = !isBurning
        val text = if (isBurning) {
            "the lighter is burning"
        } else {
            "the lighter is off"
        }
        GameController.addDialog(text, Characters.NARRATOR)
    }
}