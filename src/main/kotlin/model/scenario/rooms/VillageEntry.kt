package org.example.model.scenario.rooms

import org.example.controller.GameController
import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection
import org.example.model.scenario.Characters
import org.example.model.scenario.Rooms

val CASTLE_CONNECTION = RoomConnection(Rooms.castleEntry)

class VillageEntry: Room("Village", listOf(CASTLE_CONNECTION)) {
    var gameStart = true

    override fun onEnter(character: Character?) {
        if (gameStart) {
            GameController.addDialog(Characters.NARRATOR.DIALOG_ENTRY, Characters.NARRATOR)
            gameStart = false
        }
    }
}
