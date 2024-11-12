package org.example.model.scenario.roomobjects.castle.utilityroom

import org.example.controller.GameController
import org.example.model.common.RoomObject
import org.example.model.scenario.Characters

class Workbench : RoomObject("Workbench") {
    override fun interact() {
        GameController.addDialog(
            "Workbench with some tools.",
            Characters.NARRATOR
        )
    }
}