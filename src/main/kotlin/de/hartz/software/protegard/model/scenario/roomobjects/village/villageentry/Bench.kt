package de.hartz.software.protegard.model.scenario.roomobjects.village.villageentry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Bench : RoomObject("Bench") {
    override fun interact() {
        GameController.addDialog("You sat down", Characters.NARRATOR)
    }
}