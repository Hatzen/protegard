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

        // TODO: Someone could fall or been thrown against it, so it breaks and we could use splitter to bound light into the chapel to activate something
    }
}