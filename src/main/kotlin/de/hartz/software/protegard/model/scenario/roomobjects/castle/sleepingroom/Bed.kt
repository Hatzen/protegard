package de.hartz.software.protegard.model.scenario.roomobjects.castle.sleepingroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Bed : RoomObject("Bed") {
    override fun interact() {
        val minutes = 12 * 60L
        GameController.environment.passTime(minutes)
        GameController.addDialog(
            "You feel well rested.",
            Characters.NARRATOR
        )
    }
}