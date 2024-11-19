package de.hartz.software.protegard.model.scenario.roomobjects.castle.chapel

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class StainedGlassWindow : RoomObject("StainedGlassWindow") {
    override fun interact() {
        GameController.addDialog(
            "The windows are pretty and colorful.",
            Characters.NARRATOR
        )
        // TODO: The light at a specific time could be needed to activate a mechanism or to make symbols readable at the wall
    }
}