package de.hartz.software.protegard.model.scenario.roomobjects.castle.lobby

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Items

class OrnamentalClock : RoomObject("Ornamental Clock") {
    override fun interact() {
        GameController.addDialog(
            "A book shelve, full of different kind of books in very different conditions.",
            Characters.NARRATOR
        )
        Items.watch.setTimeByProperClock()
    }
}