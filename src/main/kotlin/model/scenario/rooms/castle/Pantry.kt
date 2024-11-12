package org.example.model.scenario.rooms.castle

import org.example.model.common.Character
import org.example.model.common.Room
import org.example.model.common.RoomConnection
import org.example.model.scenario.Rooms

class Pantry : Room("Pantry") {
    override fun initConnections() {
        connections.add(RoomConnection("enter kitchen", Rooms.kitchen))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
