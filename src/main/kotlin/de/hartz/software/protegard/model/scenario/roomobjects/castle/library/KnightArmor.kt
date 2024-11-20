package de.hartz.software.protegard.model.scenario.roomobjects.castle.library

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class KnightArmor : RoomObject("de.hartz.software.protegard.controller.puzzels.Knight Armor") {
    override fun interact() {
        GameController.addDialog(
            "An ancient knight armor. It does not seem very advanced or heavy, but more like a cult costume.",
            Characters.NARRATOR
        )
    }
}