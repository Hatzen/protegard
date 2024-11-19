package de.hartz.software.protegard.model.scenario.roomobjects.castle.tower

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class WindChimes : RoomObject("Book Shelve") {
    override fun interact() {
        GameController.addDialog(
            "A book shelve, full of different kind of books in very different conditions.",
            Characters.NARRATOR
        )
    }
}