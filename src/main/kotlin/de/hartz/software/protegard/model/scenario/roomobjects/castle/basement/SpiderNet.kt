package de.hartz.software.protegard.model.scenario.roomobjects.castle.basement

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class SpiderNet : RoomObject("a bunch of huge spider webs") {
    override fun interact() {
        GameController.addDialog(
            "Spider webs, quite sticky.",
            Characters.NARRATOR
        )
    }
}