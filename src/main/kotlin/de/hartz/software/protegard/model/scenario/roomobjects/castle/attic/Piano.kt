package de.hartz.software.protegard.model.scenario.roomobjects.castle.attic

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Piano : RoomObject("Piano") {
    override fun interact() {
        GameController.addDialog(
            "Some sounds are proper some sound like someone stepped on a cat.",
            Characters.NARRATOR
        )
    }
}