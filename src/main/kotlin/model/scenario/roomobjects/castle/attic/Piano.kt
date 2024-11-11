package org.example.model.scenario.roomobjects.castle.attic

import model.RoomObject
import org.example.controller.GameController
import org.example.model.scenario.Characters

class Piano : RoomObject("Piano") {
    override fun interact() {
        GameController.addDialog(
            "Some sounds are proper some sound like someone stepped on a cat.",
            Characters.NARRATOR
        )
    }
}