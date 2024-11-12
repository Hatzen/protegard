package org.example.model.scenario.rooms.castle

import org.example.model.common.Character
import org.example.model.common.Room
import org.example.model.common.RoomConnection
import org.example.model.scenario.Rooms

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
