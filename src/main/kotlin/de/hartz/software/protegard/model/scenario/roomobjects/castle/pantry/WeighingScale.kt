package de.hartz.software.protegard.model.scenario.roomobjects.castle.pantry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class WeighingScale : RoomObject("Flour") {
    override fun interact() {
        GameController.addDialog(
            "A large bag full of flour",
            Characters.NARRATOR
        )
        // TODO: use to pour on the ground and come back later to see where the rat come from and goes to. Catch it so someone provides help or to catch a key?
    }
}