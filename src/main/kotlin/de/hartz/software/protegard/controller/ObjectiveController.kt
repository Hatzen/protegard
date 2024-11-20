package de.hartz.software.protegard.controller

import de.hartz.software.protegard.model.milestones.Milestone
import mu.KotlinLogging

object ObjectiveController {
    fun getCurrentObjective(): String {
        return if (Milestone.TRIED_TO_ACCESS_STUDY_ROOM.reached) {
            "Get into the Studyroom"
        } else if (Milestone.FIRST_TIME_CASTLE.reached) {
            "Get informations about Sir Hansen"
        } else if (GameController.gamestate.reachedMilestone.isEmpty()) {
            "Get to the castle"
        } else {
            "Finish the game"
        }
    }
}