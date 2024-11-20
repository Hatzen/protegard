package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Hall : Room("Hall") {
    override fun initConnections() {
        connections.add(object :RoomConnection("enter my sleepingroom", Rooms.sleepingroom) {
            override fun preconditionToIdentify(): Boolean {
                return Milestone.SLEEPING_ROOM_GRANTED.reached
            }
        })
        connections.add(RoomConnection("enter Sleepingroom", Rooms.castleLadySleepingroom) { "It is not a good idea to go there." })
        connections.add(RoomConnection("enter service sleeping room", Rooms.serviceSleepingroom) { "Why should i go there?" })

        connections.add(RoomConnection("enter diningRoom", Rooms.diningRoom))
        connections.add(RoomConnection("enter lobby", Rooms.lobbyRoom))
        connections.add(RoomConnection("enter attic", Rooms.attic))
        connections.add(RoomConnection("enter utility room", Rooms.utilityRoom))
        connections.add(RoomConnection("enter library", Rooms.library))
        connections.add(RoomConnection("enter study room", Rooms.studyroom, Rooms.studyroom::handleCanEnter ) )
        connections.add(RoomConnection("leave", Rooms.castleEntry))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.chandelier,
                RoomObjects.hallPaintingStars,
                RoomObjects.hallPaintingDarkforest,
                RoomObjects.hallPaintingSirHansen,
                RoomObjects.sofa,
                RoomObjects.tapestries
            ))
    }

    override fun onEnter(character: Character?) {
    }
}
