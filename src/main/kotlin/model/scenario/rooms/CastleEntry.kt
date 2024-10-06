package org.example.model.scenario.rooms

import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection

class CastleEntry: Room("Castle", listOf()) {
    override fun onEnter(character: Character?) {
    }
}
