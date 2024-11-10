package org.example.model.scenario.rooms.castle

import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection
import org.example.model.scenario.Rooms

class Sleepingroom : Room("Sleepingroom") {
    override fun initConnections() {
        connections.add(RoomConnection("go to bathroom", Rooms.bathroom))
        connections.add(RoomConnection("enter hall", Rooms.hall))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
