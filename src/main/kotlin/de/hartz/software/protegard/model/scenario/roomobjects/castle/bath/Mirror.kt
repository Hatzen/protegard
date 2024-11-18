package de.hartz.software.protegard.model.scenario.roomobjects.castle.bath

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Mirror : RoomObject("Mirror") {
    override fun interact() {
        GameController.addDialog(
            "The mirror is clean and of good quality still it has some stains.",
            Characters.NARRATOR
        )
    }
}