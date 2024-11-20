package de.hartz.software.protegard.model.scenario.roomobjects.castle.pantry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters

class Flour : RoomObject("Flour") {
    override fun interact() {
        if (Milestone.FIND_MOUSE.reached && !Milestone.FOLLOW_MOUSE.reached) {
            GameController.addDialog(
                "You pour flour all over the floor, when the mouse comes into or leaves you will see its way.",
                Characters.NARRATOR
            )
            GameController.gamestate.setReached(Milestone.FOLLOW_MOUSE)
        } else {
            GameController.addDialog(
                "A large bag full of flour",
                Characters.NARRATOR
            )
        }
    }
}