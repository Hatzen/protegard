package de.hartz.software.protegard.model.scenario.roomobjects.castle.chapel

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Altar : RoomObject("Altar") {
    override fun interact() {
        GameController.addDialog(
            "Decorated with candles and a crucifix.",
            Characters.NARRATOR
        )
        // TODO: There could be a special candle where someone kept a key in it.
    }
}