package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class DiningRoom : Room("DiningRoom") {
    override fun initConnections() {
        connections.add(RoomConnection("enter hall", Rooms.hall))
        connections.add(RoomConnection("enter kitchen", Rooms.kitchen))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.barometer,
                RoomObjects.diningTable,
                RoomObjects.servingBell,
                RoomObjects.sideboard
            ))
    }

    override fun onEnter(character: Character?) {
    }
}
