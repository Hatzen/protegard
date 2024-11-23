package de.hartz.software.protegard.model.common.environment

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.time.Duration.Companion.seconds

class Environment {

    companion object {
        val GAME_TIME_FASTER_FACTOR = 6
    }

    var currentGameDateTime: LocalDateTime = LocalDateTime.now()
    var startTime: LocalDateTime = LocalDateTime.now()

    // TODO Chemicals could be different, sweat leading to seeing through paper
    var temperature = Heat.OK
    var hue = Hue.OK

    // TODO: Oxygen, Magnetism, Humiditiy (at glass shows hand print or something)

    init {
        val currentDate = LocalDate.of(1920, 9, 11)
        val currentTime = LocalTime.of(19, 36, 0, 0)
        currentGameDateTime = LocalDateTime.of(currentDate, currentTime)
    }

    fun refreshAndGetTime(): LocalDateTime {
        // e.g. 30 min
        val diff = startTime.until(LocalDateTime.now(), ChronoUnit.SECONDS)
        val gameTimeWithOneSecondIsOneMinute = diff.seconds.times(GAME_TIME_FASTER_FACTOR).absoluteValue.inWholeMinutes
        currentGameDateTime = currentGameDateTime.plusMinutes(gameTimeWithOneSecondIsOneMinute)
        passTime(gameTimeWithOneSecondIsOneMinute)
        return currentGameDateTime
    }

    fun passTime(gameMinutes: Long) {
        currentGameDateTime = currentGameDateTime.plusMinutes(gameMinutes)
    }
}