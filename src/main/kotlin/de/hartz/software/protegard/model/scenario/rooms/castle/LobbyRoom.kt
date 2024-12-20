package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class LobbyRoom : Room("LobbyRoom") {
    override fun initConnections() {
        connections.add(RoomConnection("enter hall", Rooms.hall))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.coatRack,
                RoomObjects.ornamentalClock,
                RoomObjects.stoneStatue,
                RoomObjects.chesstable
            )
        )
    }

    override fun onEnter(character: Character?) {
    }
}
