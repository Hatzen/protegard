package org.example.model.scenario.roomobjects.village.villageentry

import org.example.controller.GameController
import org.example.model.common.RoomObject
import org.example.model.scenario.Characters

class Bench : RoomObject("Bench") {
    override fun interact() {
        GameController.addDialog("You sat down", Characters.NARRATOR)
    }
}