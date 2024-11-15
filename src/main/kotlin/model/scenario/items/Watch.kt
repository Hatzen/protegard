package org.example.model.scenario.items

import model.common.environment.Hue
import org.example.controller.GameController
import org.example.extensions.format
import org.example.model.common.Item
import org.example.model.common.environment.Callback
import org.example.model.common.environment.CountDownTimer
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class Watch : Item("Watch") {

    var isCounting = true
    var lastTime = LocalTime.now()
    var delay: Long = 0
    val callback: Callback

    init {
        callback = Callback({
            val currentTime = GameController.environment.refreshAndGetTime()
            lastTime = currentTime.toLocalTime()
            isCounting = false
            reset()
        }, 60, 1)
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