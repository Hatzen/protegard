package org.example.model.scenario.roomobjects.castle.utilityroom

import org.example.controller.GameController
import org.example.model.common.RoomObject
import org.example.model.scenario.Characters

class SaltBucket : RoomObject("SaltBucket") {
    override fun interact() {
        GameController.addDialog(
            "A bucket of salt to avoid ice.",
            Characters.NARRATOR
        )
    }
}