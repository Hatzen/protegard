package de.hartz.software.protegard.model.scenario.roomobjects.castle.pantry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Weights : RoomObject("Weights") {
    override fun interact() {
        GameController.addDialog(
            "A full set of weights 1g, 5g, 10g, 20g, 25g, 50g, 100g, 200g, 250g, 500g",
            Characters.NARRATOR
        )
    }
}