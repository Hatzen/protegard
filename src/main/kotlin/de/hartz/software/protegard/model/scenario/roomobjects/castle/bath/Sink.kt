package de.hartz.software.protegard.model.scenario.roomobjects.castle.bath

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Sink : RoomObject("Sink") {
    override fun interact() {
        GameController.addDialog(
            "A simple ceramic sink, it even has fluent water.",
            Characters.NARRATOR
        )
    }
}