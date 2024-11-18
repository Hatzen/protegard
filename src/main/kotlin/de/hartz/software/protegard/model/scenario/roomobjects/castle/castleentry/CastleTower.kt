package de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class CastleTower : RoomObject("CastleTower") {
    override fun interact() {
        GameController.addDialog(
            "The castle looks impressive the old tower looks intact but it is hard to tell if that is true.",
            Characters.NARRATOR
        )
    }
}