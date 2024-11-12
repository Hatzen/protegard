package org.example.model.scenario.rooms.castle

import org.example.model.common.Character
import org.example.model.common.Room
import org.example.model.common.RoomConnection
import org.example.model.scenario.Rooms

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

    }

    override fun onEnter(character: Character?) {
    }
}
