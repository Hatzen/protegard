package org.example.model.scenario.rooms.castle

import org.example.model.common.Character
import org.example.model.common.Room
import org.example.model.common.RoomConnection
import org.example.model.scenario.Rooms

class Attic : Room("Attic") {
    override fun initConnections() {
        connections.add(RoomConnection("go outside the tower", Rooms.towerRightSideOutside))
        connections.add(RoomConnection("enter hall", Rooms.hall))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
