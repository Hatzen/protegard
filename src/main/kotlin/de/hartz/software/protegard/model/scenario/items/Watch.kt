package de.hartz.software.protegard.model.scenario.items

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.extensions.format
import de.hartz.software.protegard.model.common.Item
import de.hartz.software.protegard.model.common.environment.CountDownCallback
import de.hartz.software.protegard.model.common.environment.CountDownCallbackIds
import de.hartz.software.protegard.model.common.environment.CountDownTimer
import de.hartz.software.protegard.model.common.environment.Environment.Companion.GAME_TIME_FASTER_FACTOR
import de.hartz.software.protegard.model.common.environment.Hue
import de.hartz.software.protegard.model.scenario.Characters
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class Watch : Item("Watch") {

    var isCounting = true
    var lastTime = LocalTime.now()
    var delay: Long = 0
    val callback: CountDownCallback

    init {
        val TWO_HOURS_GAME_TIME_IN_SECONDS = 20 * GAME_TIME_FASTER_FACTOR
        callback = CountDownCallback({
            val currentTime = GameController.environment.refreshAndGetTime()
            lastTime = currentTime.toLocalTime()
            isCounting = false
            reset()
        }, TWO_HOURS_GAME_TIME_IN_SECONDS, CountDownCallbackIds.WATCH)
        CountDownTimer.callbacks.add(callback)
    }

    override fun interact() {
        if (GameController.environment.hue.value >= Hue.OK.value) {
            val currentTime = GameController.environment.refreshAndGetTime()
            if (!isCounting) {
                GameController.addDialog(
                    "Oh no.. The watch were not wind up anymore, the time will be wrong..",
                    Characters.MAIN_CHARACTER
                )
                delay = lastTime.until(currentTime, ChronoUnit.MINUTES)
                GameController.addDialog(lastTime.format(), this)
            } else {
                lastTime = currentTime.toLocalTime().plusMinutes(delay)
                GameController.addDialog(lastTime.format(), this)
            }
        } else {
            GameController.addDialog("Cannot tell the time when its dark", this)
        }
        windUpWatch()
        isCounting = true
    }

    private fun windUpWatch() {
        GameController.addDialog(
            "So it is wind up now, i need to rewind it in 2 hours or it wont be accurate anymore.",
            Characters.MAIN_CHARACTER
        )
        callback.reset()
    }

    // TODO: Make interact customizable with commands.
    fun setTimeByProperClock() {
        if (delay > 0) {
            val time = lastTime.format()
            GameController.addDialog(
                "Ok i was behind the time, now it is in time again. It is $time",
                Characters.MAIN_CHARACTER
            )
        }
        delay = 0
    }
}