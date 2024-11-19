package de.hartz.software.protegard.model.scenario.roomobjects.castle.chapel

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class WoodenObelisk : RoomObject("WoodenObelisk") {
    override fun interact() {
        GameController.addDialog(
            "Ornate Wooden WoodenObelisk, it looks old. There are no clues what it meaning could be.",
            Characters.NARRATOR
        )
    }
}