package de.hartz.software.protegard.model.scenario.roomobjects.castle.pantry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class CheeseWheel : RoomObject("Flour") {
    override fun interact() {
        GameController.addDialog(
            "A large bag full of flour",
            Characters.NARRATOR
        )
    }
}