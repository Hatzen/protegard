package de.hartz.software.protegard.model.scenario.roomobjects.castle.attic

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class WoodenChest : RoomObject("WoodenChest") {
    override fun interact() {
        GameController.addDialog(
            "A chest with a lock, the lock seems to be old, but not easy to break.",
            Characters.NARRATOR
        )
    }
}