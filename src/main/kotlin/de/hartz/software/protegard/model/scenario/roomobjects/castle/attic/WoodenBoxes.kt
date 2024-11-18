package de.hartz.software.protegard.model.scenario.roomobjects.castle.attic

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class WoodenBoxes : RoomObject("WoodenBoxes") {
    override fun interact() {
        GameController.addDialog(
            "There are many boxes with all kind of stuff. But most seem to be broken or old paintings, little figures, nothing useful so far.",
            Characters.NARRATOR
        )
    }
}