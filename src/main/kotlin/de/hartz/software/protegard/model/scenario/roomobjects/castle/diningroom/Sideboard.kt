package de.hartz.software.protegard.model.scenario.roomobjects.castle.diningroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Sideboard : RoomObject("Sideboard") {
    override fun interact() {
        GameController.addDialog(
            "Storing plates, silverware and the like.",
            Characters.NARRATOR
        )
    }
}