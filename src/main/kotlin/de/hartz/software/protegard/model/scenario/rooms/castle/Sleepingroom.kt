package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Sleepingroom : Room("Sleepingroom") {
    override fun initConnections() {
        connections.add(RoomConnection("enter hall", Rooms.hall))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.bed,
                RoomObjects.bedSideTable,
                RoomObjects.personalChest
            ))
    }

    override fun onEnter(character: Character?) {
    }

}
