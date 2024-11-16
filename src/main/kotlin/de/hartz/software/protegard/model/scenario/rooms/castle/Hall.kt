package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.Rooms

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
