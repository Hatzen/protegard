package org.example.model.scenario.rooms.castle

import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection
import org.example.model.scenario.Rooms

class Kitchen : Room("Kitchen") {
    override fun initConnections() {
        connections.add(RoomConnection("enter diningroom", Rooms.diningRoom))
        connections.add(RoomConnection("enter pantry", Rooms.pantry))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
