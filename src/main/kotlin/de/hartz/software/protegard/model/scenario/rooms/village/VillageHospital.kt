package de.hartz.software.protegard.model.scenario.rooms.village

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.Rooms


class VillageHospital : Room("Hospital") {

    override fun initConnections() {
        connections.add(RoomConnection("Village mainstreet", Rooms.villageEntry))
    }

    override fun initRoomObjects() {
    }

    override fun onEnter(character: Character?) {
    }
}
