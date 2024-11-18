package de.hartz.software.protegard.model.scenario.roomobjects.castle.basement

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Pump : RoomObject("Pump") {
    override fun interact() {
        GameController.addDialog(
            "A pump getting ground water.",
            Characters.NARRATOR
        )
    }
}