package de.hartz.software.protegard.model.scenario.roomobjects.castle.kitchen

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class FireStoven : RoomObject("FireStoven") {
    override fun interact() {
        GameController.addDialog(
            "A big stoven powered by coal.",
            Characters.NARRATOR
        )
    }
}