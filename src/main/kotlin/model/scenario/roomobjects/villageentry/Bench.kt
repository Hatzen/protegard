package org.example.model.scenario.roomobjects.villageentry

import model.RoomObject
import org.example.controller.GameController
import org.example.model.scenario.Characters

class Bench : RoomObject("Bench") {
    override fun interact() {
        GameController.addDialog("You sat down", Characters.NARRATOR)
    }
}