package org.example.model.scenario.roomobjects.castle.utilityroom

import org.example.controller.GameController
import org.example.model.common.RoomObject
import org.example.model.scenario.Characters

class Ladder : RoomObject("Ladder") {
    override fun interact() {
        GameController.addDialog(
            "A ladder to enlight higher lamps.",
            Characters.NARRATOR
        )
    }
}