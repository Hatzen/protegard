package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Rooms

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
        if (!GameController.gamestate.reached(Milestone.FIRST_TIME_CASTLE)) {
            GameController.startDialog(Characters.AKSEL_BRANDT_THE_GUARD)
            GameController.gamestate.reached(Milestone.FIRST_TIME_CASTLE)
        }
    }
}
