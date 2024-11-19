package de.hartz.software.protegard.model.scenario.items

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.extensions.format
import de.hartz.software.protegard.model.common.Item
import de.hartz.software.protegard.model.common.environment.CountDownCallback
import de.hartz.software.protegard.model.common.environment.CountDownCallbackIds
import de.hartz.software.protegard.model.common.environment.CountDownTimer
import de.hartz.software.protegard.model.common.environment.Hue
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class Watch : Item("Watch") {

    var isCounting = true
    var lastTime = LocalTime.now()
    var delay: Long = 0
    val callback: CountDownCallback

    init {
        callback = CountDownCallback({
            val currentTime = GameController.environment.refreshAndGetTime()
            lastTime = currentTime.toLocalTime()
            isCounting = false
            reset()
        }, 60, CountDownCallbackIds.WATCH)
        CountDownTimer.callbacks.add(callback)
    }

    override fun interact() {
        if (GameController.environment.hue.value >= Hue.OK.value) {
            val currentTime = GameController.environment.refreshAndGetTime()
            if (!isCounting) {
                delay = lastTime.until(currentTime, ChronoUnit.MINUTES)
                GameController.addDialog(lastTime.format(), this)
            } else {
                lastTime = currentTime.toLocalTime().plusMinutes(delay)
                GameController.addDialog(lastTime.format(), this)
                return
            }
        } else {
            GameController.addDialog("Cannot tell the time when its dark", this)
        }
        windUpWatch()
        isCounting = true
    }

    private fun windUpWatch() {
        callback.reset()
    }

    // TODO: Make interact customizable with commands.
    fun setTimeByProperClock() {
        delay = 0
    }
}