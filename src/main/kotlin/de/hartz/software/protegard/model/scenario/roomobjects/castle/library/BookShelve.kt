package de.hartz.software.protegard.model.scenario.roomobjects.castle.library

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class BookShelve : RoomObject("Book Shelve") {
    override fun interact() {
        GameController.addDialog(
            "A book shelve, full of different kind of books in very different conditions.",
            Characters.NARRATOR
        )
    }
}