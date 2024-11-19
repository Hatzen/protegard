package de.hartz.software.protegard.model.scenario.roomobjects.castle.kitchen

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class IronSink : RoomObject("IronSink") {
    override fun interact() {
        GameController.addDialog(
            "A sink with fluent water.",
            Characters.NARRATOR
        )
    }
}