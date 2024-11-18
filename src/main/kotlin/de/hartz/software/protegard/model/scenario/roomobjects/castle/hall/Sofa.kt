package de.hartz.software.protegard.model.scenario.roomobjects.castle.hall

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Sofa : RoomObject("Sofa") {
    override fun interact() {
        GameController.addDialog(
            "You sit down, its more comfortable than it looked.",
            Characters.NARRATOR
        )
    }
}