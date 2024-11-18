package de.hartz.software.protegard.model.scenario.roomobjects.castle.basement

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class CoalPile : RoomObject("Coal pile") {
    override fun interact() {
        GameController.addDialog(
            "There is plenty of coal here, this winter should be sufficient.",
            Characters.NARRATOR
        )
    }
}