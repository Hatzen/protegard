package org.example.model.scenario.rooms.castle

import org.example.model.common.Character
import org.example.model.common.Room
import org.example.model.common.RoomConnection
import org.example.model.scenario.Rooms

/*
Secret room which can be found only after resolving a riddle in the studyroom.
 */
class SecretStudyroom : Room("SecretStudyroom") {
    override fun initConnections() {
        connections.add(RoomConnection("go back to studyroom", Rooms.studyroom))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
