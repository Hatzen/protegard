package de.hartz.software.protegard.model.scenario.roomobjects.castle.utilityroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Broom : RoomObject("Broom") {
    override fun interact() {
        GameController.addDialog(
            "A casual broom.",
            Characters.NARRATOR
        )
    }
}