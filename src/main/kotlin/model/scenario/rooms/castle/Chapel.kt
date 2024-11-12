package org.example.model.scenario.rooms.castle

import org.example.model.common.Character
import org.example.model.common.Room
import org.example.model.common.RoomConnection
import org.example.model.scenario.Rooms

class Chapel : Room("Chapel") {
    override fun initConnections() {
        connections.add(RoomConnection("Go to castle entry", Rooms.castleEntry))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
