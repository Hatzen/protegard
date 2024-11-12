package org.example.model.scenario.rooms.castle

import org.example.model.common.Character
import org.example.model.common.Room
import org.example.model.common.RoomConnection
import org.example.model.scenario.Rooms

class Hall : Room("Hall") {
    override fun initConnections() {
        connections.add(RoomConnection("enter diningRoom", Rooms.diningRoom))
        connections.add(RoomConnection("enter lobby", Rooms.lobbyRoom))
        connections.add(RoomConnection("enter attic", Rooms.attic))
        connections.add(RoomConnection("enter utility room", Rooms.utilityRoom))
        connections.add(RoomConnection("enter library", Rooms.library))
        connections.add(RoomConnection("enter study room", Rooms.studyroom))
        connections.add(RoomConnection("leave", Rooms.castleEntry))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
