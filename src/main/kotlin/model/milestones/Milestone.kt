package org.example.model.milestones

enum class Milestone(var reached: Boolean = false) : IMilestone {
    FIRST_TIME_CASTLE;

    override fun isReached(): Boolean = reached
}