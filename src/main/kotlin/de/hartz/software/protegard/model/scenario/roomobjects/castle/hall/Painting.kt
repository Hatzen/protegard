package de.hartz.software.protegard.model.scenario.roomobjects.castle.hall

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class HallPainting(name: String) : RoomObject(name) {
    val description: String by lazy {
        GameController.randomAnswerController.getDescriptionForObject("A painting within an old castle hall in 1920, painted years ago.")
    }

    override fun interact() {
        GameController.addDialog(
            description,
            Characters.NARRATOR
        )
    }
}