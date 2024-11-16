package org.example.model.scenario.rooms.castle

import org.example.controller.GameController
import org.example.model.common.Character
import org.example.model.common.Room
import org.example.model.common.RoomConnection
import org.example.model.milestones.Milestone
import org.example.model.scenario.Characters
import org.example.model.scenario.Rooms

class CastleEntry : Room("CastleEntry Outdoor") {
    override fun initConnections() {
        connections.add(RoomConnection("Street to village", Rooms.villageEntry))
        connections.add(RoomConnection("Go inside", Rooms.hall))
        connections.add(RoomConnection("Go to the chapel", Rooms.chapel))
        connections.add(RoomConnection("go to utility room", Rooms.utilityRoom))
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
        if (GameController.gamestate.reached(Milestone.FIRST_TIME_CASTLE)) {
            GameController.startDialog(Characters.AKSEL_BRANDT_THE_GUARD)
            GameController.gamestate.reached(Milestone.FIRST_TIME_CASTLE)
        }
    }
}
