package org.example.model.scenario.roomobjects.castle.utilityroom

import model.RoomObject
import org.example.controller.GameController
import org.example.model.scenario.Characters

class Broom : RoomObject("Broom") {
    override fun interact() {
        GameController.addDialog(
            "A casual broom.",
            Characters.NARRATOR
        )
    }
}