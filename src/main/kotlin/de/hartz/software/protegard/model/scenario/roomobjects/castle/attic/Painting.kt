package de.hartz.software.protegard.model.scenario.roomobjects.castle.attic

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Painting : RoomObject("Painting") {
    override fun interact() {
        GameController.addDialog(
            "The painting seems to be old, but professional",
            Characters.NARRATOR
        )
        // TODO: there could be some hint on the backsite or it could be overpainted or the frame could be interesting with informations.
    }
}