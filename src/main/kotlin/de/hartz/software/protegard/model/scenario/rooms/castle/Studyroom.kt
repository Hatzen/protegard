package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Items
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Studyroom : Room("Studyroom") {
    override fun initConnections() {
        connections.add(RoomConnection("enter hall", Rooms.hall))
        connections.add(object : RoomConnection("enter unknown area", Rooms.secretStudyroom) {
            override fun preconditionToIdentify(): Boolean {
                return Milestone.SECRET_STUDY_ROOM_FOUND.reached
            }
        })
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.desk,
                RoomObjects.metalTray
            )
        )
    }

    override fun onEnter(character: Character?) {
    }

    fun handleCanEnter(): String? {
        val hasKey = Characters.MAIN_CHARACTER.inventory.contains(Items.studyRoomKey)
        if (!hasKey) {
            if (!Milestone.TRIED_TO_ACCESS_STUDY_ROOM.reached) {
                GameController.gamestate.setReached(Milestone.TRIED_TO_ACCESS_STUDY_ROOM)
                val lady = Characters.ASTRID_HANSEN_LADY_OF_THE_CASTLE
                lady.addDialog(
                    Characters.ASTRID_HANSEN_LADY_OF_THE_CASTLE.DENIED_STUDY_ROOM_KEY
                )
            }
            return "The door is locked the key is needed, probably i have to look for it or ask someone."
        }
        return null
    }
}
