package de.hartz.software.protegard.model.scenario.rooms.village

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.milestones.Chapter
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms


class VillageEntry : Room("Village") {

    override fun initConnections() {
        connections.add(RoomConnection("Street to castle", Rooms.castleEntry))
        connections.add(RoomConnection("Village Hospital", Rooms.villageHospital) {
            if (GameController.gamestate.currentChapter == Chapter.CHAPTER2) {
                null
            } else {
                "i dont feel sick."
            }
        })
    }

    override fun initRoomObjects() {
        objects.add(RoomObjects.bench)
        objects.add(RoomObjects.newspaperStore)
        objects.add(RoomObjects.rareCoin)
    }

    override fun onEnter(character: Character?) {
    }
}
