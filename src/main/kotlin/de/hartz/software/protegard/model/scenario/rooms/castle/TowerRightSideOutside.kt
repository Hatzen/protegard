package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class TowerRightSideOutside : Room("TowerRightSideOutside") {
    override fun initConnections() {
        connections.add(RoomConnection("go back to attic", Rooms.attic))

    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.pigeonCoop,
                RoomObjects.windChimes
            ))
    }

    override fun onEnter(character: Character?) {
    }
}
