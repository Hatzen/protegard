package de.hartz.software.protegard.model.scenario.roomobjects.castle.bath

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Boiler : RoomObject("Boiler") {
    override fun interact() {
        GameController.addDialog(
            "This boiler is to small and has no way to but fuels in, it looks like the pipes go into the basement. There is only one valve that seems to not being used in a while.",
            Characters.NARRATOR
        )
    }
}