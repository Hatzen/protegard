package org.example.model.scenario.rooms.castle

import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection
import org.example.model.scenario.Rooms

class Bathroom : Room("Bathroom") {
    override fun initConnections() {
        connections.add(RoomConnection("enter sleeping room", Rooms.sleepingroom))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
