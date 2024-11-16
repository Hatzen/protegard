package de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Well : RoomObject("Well") {
    override fun interact() {
        GameController.addDialog(
            "The water could be cleaned. You better not stick your fingers in it.",
            Characters.NARRATOR
        )
    }
}