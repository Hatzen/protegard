package org.example.model.scenario.rooms

import org.example.model.Character
import org.example.model.Room
import org.example.model.RoomConnection
import org.example.model.scenario.Rooms


class VillageHospital : Room("Hospital") {

    override fun initConnections() {
        connections.add(RoomConnection("Village mainstreet", Rooms.villageEntry))
    }

    override fun initRoomObjects() {
    }

    override fun onEnter(character: Character?) {
    }
}
