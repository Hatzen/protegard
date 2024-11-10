package org.example.model.scenario.rooms.castle

import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection
import org.example.model.scenario.Rooms

class TowerRightSideOutside : Room("TowerRightSideOutside") {
    override fun initConnections() {
        connections.add(RoomConnection("go back to attic", Rooms.attic))

    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
