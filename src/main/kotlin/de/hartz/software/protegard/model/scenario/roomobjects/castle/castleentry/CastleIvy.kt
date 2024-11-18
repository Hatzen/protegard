package de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class CastleIvy : RoomObject("Ivy") {
    override fun interact() {
        GameController.addDialog(
            "The ivy is very old, it does not look very healthy but the roots look very big and strong, they seem to do damage to the old walls.",
            Characters.NARRATOR
        )
    }
}