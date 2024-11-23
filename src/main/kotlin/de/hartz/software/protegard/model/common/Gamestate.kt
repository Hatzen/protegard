package de.hartz.software.protegard.model.common

import de.hartz.software.protegard.controller.LoggerUtil
import de.hartz.software.protegard.model.milestones.Chapter
import de.hartz.software.protegard.model.milestones.Milestone

class Gamestate {
    // Game state.
    var currentChapter = Chapter.CHAPTER1
    var reachedMilestone: MutableList<Milestone> = mutableListOf()

    fun reached(milestone: Milestone): Boolean {
        return reachedMilestone.contains(milestone) || milestone.reached
    }

    fun setReached(milestone: Milestone) {
        LoggerUtil.logger.debug { "Milestone reached: $milestone" }
        reachedMilestone.add(milestone)
        milestone.reached = true
    }
}