package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

/*
Stores garden utils. Wheels, hoes, salt, wires.
 */
class UtilityRoom : Room("UtilityRoom") {
    override fun initConnections() {
        connections.add(RoomConnection("enter hall", Rooms.hall))
        connections.add(RoomConnection("enter basement", Rooms.basement))
        connections.add(RoomConnection("go outside", Rooms.castleEntry))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.ladder,
                RoomObjects.saltBucket,
                RoomObjects.workBench,
                RoomObjects.brush,
                RoomObjects.broom
            )
        )
    }

    override fun onEnter(character: Character?) {
    }
}
