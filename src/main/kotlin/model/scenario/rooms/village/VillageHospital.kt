package org.example.model.scenario.rooms.village

import org.example.model.common.Character
import org.example.model.common.Room
import org.example.model.common.RoomConnection
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
