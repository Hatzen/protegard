package de.hartz.software.protegard.model.scenario.roomobjects.castle.sleepingroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Bed : RoomObject("Bed") {
    override fun interact() {
        GameController.addDialog(
            "My bed to rest.",
            Characters.NARRATOR
        )
    }
}