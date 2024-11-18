package de.hartz.software.protegard.model.scenario.roomobjects.castle.bath

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Bathtub : RoomObject("Bathtub") {
    override fun interact() {
        GameController.addDialog(
            "Freestanding cast iron tub with claw feet",
            Characters.NARRATOR
        )
    }
}