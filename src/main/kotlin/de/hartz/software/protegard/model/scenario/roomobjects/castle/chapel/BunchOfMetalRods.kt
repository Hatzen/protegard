package de.hartz.software.protegard.model.scenario.roomobjects.castle.chapel

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class BunchOfMetalRods : RoomObject("BunchOfMetalRods") {
    override fun interact() {
        GameController.addDialog(
            "There are several metal rods framed in the wall. The rods have a different length, it looks weird and useless. Must be some sort of art.",
            Characters.NARRATOR
        )
        // TODO: WHen the hidden message at the chapel got read we could make a specific frequence which will make the altar invisible and let the player get downstairs to the alien?
    }
}