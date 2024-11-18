package de.hartz.software.protegard.model.scenario.roomobjects.castle.hall

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Tapestries : RoomObject("Tapestries") {
    val description: String by lazy {
        GameController.randomAnswerController.getDescriptionForObject("A Tapestries within an old castle hall in 1920.")
    }

    override fun interact() {
        GameController.addDialog(
            description,
            Characters.NARRATOR
        )
    }
}