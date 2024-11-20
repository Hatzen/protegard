package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Chapel : Room("Chapel") {
    override fun initConnections() {
        connections.add(RoomConnection("Go to castle entry", Rooms.castleEntry))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.altar,
                RoomObjects.bunchOfMetalRods,
                RoomObjects.rope,
                RoomObjects.stainedGlassWindow,
                RoomObjects.woodenObelisk
            ))
    }

    override fun onEnter(character: Character?) {
    }
}
