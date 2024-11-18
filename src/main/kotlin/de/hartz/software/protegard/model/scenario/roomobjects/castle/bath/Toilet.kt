package de.hartz.software.protegard.model.scenario.roomobjects.castle.bath

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Toilet : RoomObject("Toilet") {
    override fun interact() {
        GameController.addDialog(
            "It stinks, although there is no stinky thing in sight.",
            Characters.NARRATOR
        )
    }
}