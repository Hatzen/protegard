package de.hartz.software.protegard.model.milestones

enum class Mission(var missionStatus: MissionStatus = MissionStatus.INACTIVE) : IMilestone {
    CHAPTER_2_WANDERING_FOREST;

    override fun isReached(): Boolean = missionStatus == MissionStatus.CLOSED || missionStatus == MissionStatus.FINISHED
}

enum class MissionStatus {
    INACTIVE,
    ACTIVATED,
    INPROGRESS,
    FINISHED,
    CLOSED;
}