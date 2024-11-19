package de.hartz.software.protegard.model.scenario.roomobjects.castle.diningroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class DiningTable : RoomObject("DiningTable") {
    override fun interact() {
        GameController.addDialog(
            "A long and old dining table plain with some special curves but nothing providing hints.",
            Characters.NARRATOR
        )
    }
}