package de.hartz.software.protegard.model.scenario.roomobjects.castle.sleepingroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Bed : RoomObject("Bed") {
    override fun interact() {
        GameController.environment.currentGameDateTime = GameController.environment.currentGameDateTime.plusHours(12)
        GameController.addDialog(
            "You feel well rested.",
            Characters.NARRATOR
        )
    }
}