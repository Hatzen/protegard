package de.hartz.software.protegard.model.scenario.roomobjects.castle.studyroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Item
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.items.RareCoin

class MetalTray : RoomObject("Metal Tray") {
    override fun interact() {
        GameController.addDialog(
            "A tray it looks like something should lay on it, there is a circled notch.",
            Characters.NARRATOR
        )
    }

    override fun interact(items: Item) {
        if (items is RareCoin) {
            GameController.gamestate.setReached(Milestone.SECRET_STUDY_ROOM_FOUND)
            GameController.addDialog(
                "The coin matches perfectly and activates a mechanism. The wooden cladding opens slightly.",
                Characters.NARRATOR
            )
            GameController.nextChapter()
        }
    }
}