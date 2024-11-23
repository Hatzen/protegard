package de.hartz.software.protegard.model.scenario.roomobjects.castle.pantry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class WeighingScale : RoomObject("Weighing Scale") {
    override fun interact() {
        GameController.addDialog(
            "A simple weighning scale. You hang in the weights and place the object to weight on the other side.",
            Characters.NARRATOR
        )
    }
}