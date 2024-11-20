package de.hartz.software.protegard.model.scenario.roomobjects.castle.lobby

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.controller.puzzels.startChessGame
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class Chesstable : RoomObject("Chess table") {
    override fun interact() {
        GameController.addDialog(
            "Start playing Chess. Type x to end game. Type a0 a2 enter to move figure from a0 to a2.",
            Characters.NARRATOR
        )
        startChessGame()
    }
}