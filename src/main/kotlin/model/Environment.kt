package org.example.model

import org.example.model.milestones.Chapter
import org.example.model.milestones.Milestone
import java.time.Instant
import java.util.*

class Environment {

    var currentChapter = Chapter.CHAPTER1
    var currentMilestone: List<Milestone> = mutableListOf()
    var dateTime: Date = Date.from(Instant.now())
    var temperature = Heat.OK
    var hue = Hue.OK
}

enum class Hue(val value: Int) {
    BRIGHT(10),
    OK(0),
    DARK(-10)
    ;
}

enum class Heat(val value: Int) {
    WARM(10),
    OK(0),
    COLD(-10),
    TOO_COLD(-100)
    ;
}