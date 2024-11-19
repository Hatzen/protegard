package de.hartz.software.protegard.model.scenario.roomobjects.castle.library

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class WritingDesk : RoomObject("Barometer") {
    override fun interact() {
        GameController.addDialog(
            "The current pressure seems hight it will freeze probably. What a surprise.",
            Characters.NARRATOR
        )
    }
}