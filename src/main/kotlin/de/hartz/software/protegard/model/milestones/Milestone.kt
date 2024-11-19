package de.hartz.software.protegard.model.milestones

enum class Milestone(var reached: Boolean = false) : IMilestone {
    FIRST_TIME_CASTLE,
    // Stone Plate.
    CASTLE_CANT_READ_STONE_PLATE,
    CASTLE_CAN_READ_STONE_PLATE,

    // Lady Milestones.
    SLEEPING_ROOM_GRANTED,
    TRIED_TO_ACCESS_STUDY_ROOM,
    MISSION_TO_GET_KEY_TO_STUDY_ROOM,

    // Other.
    SECRET_STUDY_ROOM_OPENED
    ;

    override fun isReached(): Boolean = reached
}