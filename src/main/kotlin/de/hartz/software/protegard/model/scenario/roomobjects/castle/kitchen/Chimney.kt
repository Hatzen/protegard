package de.hartz.software.protegard.model.scenario.roomobjects.castle.kitchen

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Chimney : RoomObject("Chimney") {
    override fun interact() {
        GameController.addDialog(
            "An old chimney for soaking the smoke when cooking and burning trash.",
            Characters.NARRATOR
        )
    }
}