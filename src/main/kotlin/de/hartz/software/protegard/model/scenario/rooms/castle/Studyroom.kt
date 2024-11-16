package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.Rooms

class Studyroom : Room("Studyroom") {
    override fun initConnections() {
        connections.add(RoomConnection("enter hall", Rooms.hall))
        // TODO: add when solving a riddle.
        // connections.add(RoomConnection("enter unkown are", Rooms.secretStudyroom))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
