package de.hartz.software.protegard.model.scenario.roomobjects.castle.utilityroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Workbench : RoomObject("Workbench") {
    override fun interact() {
        GameController.addDialog(
            "Workbench with some tools.",
            Characters.NARRATOR
        )
    }
}