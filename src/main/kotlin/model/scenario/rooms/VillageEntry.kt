package org.example.model.scenario.rooms

import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection
import org.example.model.scenario.RoomObjects
import org.example.model.scenario.Rooms


class VillageEntry : Room("Village") {

    override fun initConnections() {
        connections.add(RoomConnection("Street to castle", Rooms.castleEntry))
    }

    override fun initRoomObjects() {
        objects.add(RoomObjects.bench)
        objects.add(RoomObjects.rareCoin)
    }

    override fun onEnter(character: Character?) {
    }
}
