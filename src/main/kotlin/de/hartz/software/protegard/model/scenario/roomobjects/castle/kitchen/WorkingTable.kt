package de.hartz.software.protegard.model.scenario.roomobjects.castle.kitchen

import controller.puzzels.CookingPuzzle
import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class WorkingTable : RoomObject("WorkingTable") {
    override fun interact() {
        GameController.addDialog(
            "The current pressure seems hight it will freeze probably. What a surprise.",
            Characters.NARRATOR
        )
        controller.puzzels.CookingPuzzle
            // TODO: Start cooking puzzel
    }
}