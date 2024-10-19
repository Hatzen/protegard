package org.example.model.milestones

enum class Milestone(var reached: Boolean = false) : IMilestone {
    ;

    override fun isReached(): Boolean = reached
}