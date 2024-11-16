package de.hartz.software.protegard.model.milestones

// TODO Something like interacted or destoryed etc? But probably better just use specific item state.
enum class Status(var reached: Boolean = false) : IMilestone {
    ;

    override fun isReached(): Boolean = reached
}