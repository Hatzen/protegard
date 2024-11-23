package de.hartz.software.protegard.model.scenario.roomobjects.castle.pantry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class GlassJars : RoomObject("Glass Jars") {
    override fun interact() {
        GameController.addDialog(
            "Jars full of different liquids and marmalade",
            Characters.NARRATOR
        )
    }
}