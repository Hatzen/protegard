package de.hartz.software.protegard.model.scenario.roomobjects.castle.utilityroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Ladder : RoomObject("Ladder") {
    override fun interact() {
        GameController.addDialog(
            "A ladder to enlight higher lamps.",
            Characters.NARRATOR
        )
    }
}