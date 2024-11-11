package org.example.model.scenario.roomobjects.castle.attic

import model.RoomObject
import org.example.controller.GameController
import org.example.model.scenario.Characters

class Painting : RoomObject("Painting") {
    override fun interact() {
        GameController.addDialog(
            "The painting seems to be old, but professional",
            Characters.NARRATOR
        )
        // TODO: there could be some hint on the backsite or it could be overpainted or the frame could be interesting with informations.
    }
}