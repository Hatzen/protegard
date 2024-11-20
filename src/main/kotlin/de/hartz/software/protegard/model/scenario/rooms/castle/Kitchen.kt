package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Kitchen : Room("Kitchen") {
    override fun initConnections() {
        connections.add(RoomConnection("enter diningroom", Rooms.diningRoom))
        connections.add(RoomConnection("enter pantry", Rooms.pantry))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.chimney,
                RoomObjects.fireStoven,
                RoomObjects.icebox,
                RoomObjects.ironSink,
                RoomObjects.workingTable
            ))
    }

    override fun onEnter(character: Character?) {
    }
}
