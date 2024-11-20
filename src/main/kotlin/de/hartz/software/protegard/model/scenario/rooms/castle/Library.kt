package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Library : Room("Library") {
    override fun initConnections() {
        connections.add(RoomConnection("enter hall", Rooms.hall))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.bookShelve,
                RoomObjects.globe,
                RoomObjects.knightArmor,
                RoomObjects.readingNook,
                RoomObjects.writingDesk
            ))
    }

    override fun onEnter(character: Character?) {
    }
}
