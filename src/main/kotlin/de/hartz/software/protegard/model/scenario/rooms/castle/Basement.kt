package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Basement : Room("Basement") {
    override fun initConnections() {
        connections.add(RoomConnection("enter utility room", Rooms.utilityRoom))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.bigBoiler,
                RoomObjects.coalPile,
                RoomObjects.pump,
                RoomObjects.spiderNet
            ))
    }

    override fun onEnter(character: Character?) {
    }
}
