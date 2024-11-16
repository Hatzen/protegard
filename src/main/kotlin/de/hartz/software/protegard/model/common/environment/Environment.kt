package de.hartz.software.protegard.model.common.environment

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.time.Duration.Companion.minutes

class Environment {
    var currentGameDateTime: LocalDateTime = LocalDateTime.now()
    var startTime: LocalDateTime = LocalDateTime.now()
    var temperature = Heat.OK
    var hue = Hue.OK

    init {
        val currentDate = LocalDate.of(1920, 9, 11)
        val currentTime = LocalTime.of(19, 36, 0, 0)
        currentGameDateTime = LocalDateTime.of(currentDate, currentTime)
    }

    fun refreshAndGetTime(): LocalDateTime {
        // e.g. 30 min
        val diff = startTime.until(LocalDateTime.now(), ChronoUnit.MINUTES)
        val gameTimeWithOneSecondIsOneMinute = diff.minutes.times(60).absoluteValue.inWholeMinutes
        currentGameDateTime.plusMinutes(gameTimeWithOneSecondIsOneMinute)
        return currentGameDateTime
    }
}