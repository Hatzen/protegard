package de.hartz.software.protegard.model.scenario.roomobjects.castle.pantry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class WoodenBarrel : RoomObject("Wooden Barrels") {
    override fun interact() {
        GameController.addDialog(
            "Different barrels, one has to open to see what is inside.",
            Characters.NARRATOR
        )
    }
}