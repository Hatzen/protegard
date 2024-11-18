package de.hartz.software.protegard.model.scenario.roomobjects.castle.basement

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class BigBoiler : RoomObject("BigBoiler") {
    override fun interact() {
        GameController.addDialog(
            "This is a big boiler, it runs on coal.",
            Characters.NARRATOR
        )
    }
}