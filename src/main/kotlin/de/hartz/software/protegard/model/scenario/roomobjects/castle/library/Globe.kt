package de.hartz.software.protegard.model.scenario.roomobjects.castle.library

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Globe : RoomObject("Globe") {
    override fun interact() {
        GameController.addDialog(
            "A Globe of the earth, there are some additional lines seems to be some sort of portolan Charts. when touching the globe it feels hollow.",
            Characters.NARRATOR
        )
    }
}