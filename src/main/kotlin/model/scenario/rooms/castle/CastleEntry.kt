package org.example.model.scenario.rooms.castle

import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection
import org.example.model.scenario.Rooms

class CastleEntry : Room("CastleEntry Outdoor") {
    override fun initConnections() {
        connections.add(RoomConnection("Street to village", Rooms.villageEntry))
        connections.add(RoomConnection("Go inside", Rooms.hall))
        connections.add(RoomConnection("Go to the chapel", Rooms.chapel))
        connections.add(RoomConnection("go to utility room", Rooms.utilityRoom))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
