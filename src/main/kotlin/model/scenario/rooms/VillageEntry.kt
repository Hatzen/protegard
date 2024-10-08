package org.example.model.scenario.rooms

import org.example.controller.GameController
import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection
import org.example.model.scenario.Characters
import org.example.model.scenario.Rooms


class VillageEntry: Room("Village") {

    override fun initConnections() {
        connections.add(RoomConnection(Rooms.castleEntry))
    }

    override fun onEnter(character: Character?) {
    }
}
