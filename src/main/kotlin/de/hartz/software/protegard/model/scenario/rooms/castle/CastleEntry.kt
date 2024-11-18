package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.CastleIvy
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.CastleTower
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.StonePlate
import de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry.Well

class CastleEntry : Room("CastleEntry Outdoor") {
    override fun initConnections() {
        connections.add(RoomConnection("Street to village", Rooms.villageEntry))
        connections.add(RoomConnection("Go inside", Rooms.hall))
        connections.add(RoomConnection("Go to the chapel", Rooms.chapel))
        connections.add(RoomConnection("go to utility room", Rooms.utilityRoom))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.castleIvy,
                RoomObjects.castleTower,
                RoomObjects.stonePlate,
                RoomObjects.well)
        )
    }

    override fun onEnter(character: Character?) {
        if (!Milestone.FIRST_TIME_CASTLE.reached) {
            GameController.startDialog(Characters.AKSEL_BRANDT_THE_GUARD)
            GameController.gamestate.setReached(Milestone.FIRST_TIME_CASTLE)
        }
    }
}
