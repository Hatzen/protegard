package de.hartz.software.protegard.model.scenario.roomobjects.castle.bath

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Radiator : RoomObject("Radiator") {
    override fun interact() {
        GameController.addDialog(
            "The radiator is directly connected to the boiler in the bathroom.",
            Characters.NARRATOR
        )
    }
}