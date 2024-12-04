package de.hartz.software.protegard.model.scenario.rooms.village

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.controller.puzzels.TextBasedMaze
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.milestones.Chapter
import de.hartz.software.protegard.model.milestones.Mission
import de.hartz.software.protegard.model.milestones.MissionStatus
import de.hartz.software.protegard.model.scenario.Characters
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
        if (!Mission.CHAPTER_2_WANDERING_FOREST.isReached()) {
            GameController.addDialog(
                "While you tried to walk to the village you went lost in the forest, find the way out",
                Characters.NARRATOR
            )
            TextBasedMaze(GameController.view).startGame()
            GameController.addDialog(
                "You found the village entry finally..",
                Characters.NARRATOR
            )
            Mission.CHAPTER_2_WANDERING_FOREST.missionStatus = MissionStatus.FINISHED
        }
    }
}
