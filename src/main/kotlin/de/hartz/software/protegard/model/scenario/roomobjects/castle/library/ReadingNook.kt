package de.hartz.software.protegard.model.scenario.roomobjects.castle.library

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class ReadingNook : RoomObject("Reading Nook") {
    override fun interact() {
        GameController.addDialog(
            "A cozy place with some comfortable chairs.",
            Characters.NARRATOR
        )
    }
}