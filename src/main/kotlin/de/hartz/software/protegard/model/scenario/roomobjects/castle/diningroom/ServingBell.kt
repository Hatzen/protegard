package de.hartz.software.protegard.model.scenario.roomobjects.castle.diningroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class ServingBell : RoomObject("ServingBell") {
    override fun interact() {
        GameController.addDialog(
            "The bell should ring when the food is served. I dont ring it know, otherwise someone gets annoyed i guess.",
            Characters.NARRATOR
        )
    }
}