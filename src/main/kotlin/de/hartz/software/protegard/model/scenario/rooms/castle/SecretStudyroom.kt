package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

/*
Secret room which can be found only after resolving a riddle in the studyroom.
 */
class SecretStudyroom : Room("SecretStudyroom") {
    override fun initConnections() {
        connections.add(RoomConnection("go back to studyroom", Rooms.studyroom))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.bunchOfManusscripts,
                RoomObjects.tinyGlowingArtifact
            ))
    }

    override fun onEnter(character: Character?) {
    }
}
