package de.hartz.software.protegard.model.scenario.roomobjects.castle.kitchen

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Icebox : RoomObject("Icebox") {
    override fun interact() {
        GameController.addDialog(
            "A box to keep beverages that need to stay cold. The ice seems a bit dirty, like it is natural. Probably it is self farmed.",
            Characters.NARRATOR
        )
    }
}