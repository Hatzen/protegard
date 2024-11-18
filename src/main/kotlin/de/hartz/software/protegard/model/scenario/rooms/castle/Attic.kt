package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Attic : Room("Attic") {
    override fun initConnections() {
        connections.add(RoomConnection("go outside the tower", Rooms.towerRightSideOutside))
        connections.add(RoomConnection("enter hall", Rooms.hall))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.painting,
                RoomObjects.woodenBoxes,
                RoomObjects.woodenChest,
                RoomObjects.storageRack,
                RoomObjects.pileOfNewspaper,
                RoomObjects.piano
            ))
    }

    override fun onEnter(character: Character?) {
    }
}
