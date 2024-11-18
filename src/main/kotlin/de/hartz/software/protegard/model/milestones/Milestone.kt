package de.hartz.software.protegard.model.milestones

enum class Milestone(var reached: Boolean = false) : IMilestone {
    FIRST_TIME_CASTLE,
    CASTLE_CANT_READ_STONE_PLATE,
    CASTLE_CAN_READ_STONE_PLATE
    ;

    override fun isReached(): Boolean = reached
}