package de.hartz.software.protegard.model.scenario.roomobjects.castle.hall

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Chandelier : RoomObject("Chandelier") {
    override fun interact() {
        GameController.addDialog(
            "The chandelier is simple but effective in providing light.",
            Characters.NARRATOR
        )
    }
}