package org.example.model.common

import org.example.model.milestones.Chapter
import org.example.model.milestones.Milestone

class Gamestate {
    // Game state.
    var currentChapter = Chapter.CHAPTER1
    var reachedMilestone: MutableList<Milestone> = mutableListOf()

    fun reached(milestone: Milestone): Boolean {
        return reachedMilestone.contains(milestone) || milestone.reached
    }

    fun setReached(milestone: Milestone) {
        reachedMilestone.add(milestone)
        milestone.reached = true
    }
}