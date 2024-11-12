package org.example.model.scenario.roomobjects.castle.castleentry

import org.example.controller.GameController
import org.example.model.common.RoomObject
import org.example.model.scenario.Characters

class Well : RoomObject("Well") {
    override fun interact() {
        GameController.addDialog(
            "The water could be cleaned. You better not stick your fingers in it.",
            Characters.NARRATOR
        )
    }
}