package de.hartz.software.protegard.model.scenario.roomobjects.village.villageentry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Rooms

class Bench : RoomObject("Bench") {
    override fun interact() {
        GameController.addDialog("You sat down", Characters.NARRATOR)
        if (Milestone.USE_BENCH_WAIT.reached) {
            GameController.environment.passTime(2 * 60L)
            GameController.addDialog(
                "You waited 2 hours and then the taxi dropped you off at the castle.",
                Characters.NARRATOR
            )
            Characters.MAIN_CHARACTER.gotoRoom(Rooms.castleEntry)
        }
    }
}