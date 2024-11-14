package org.example.model.scenario.items

import model.common.environment.Hue
import org.example.controller.GameController
import org.example.extensions.format
import org.example.model.common.Item
import org.example.model.common.environment.Callback
import org.example.model.common.environment.CountDownTimer
import java.time.LocalTime

class Watch : Item("Watch") {

    var isCounting = true
    var lastTime = LocalTime.now()
    val callback: Callback

    init {
        callback = Callback({
            isCounting = false
            reset()
        }, 60, 1)
        CountDownTimer.callbacks.add(callback)
    }

    override fun interact() {
        if (!isCounting) {
            GameController.addDialog(lastTime.format(), this)
        } else if (GameController.environment.hue.value >= Hue.OK.value) {
            val currentTime = GameController.environment.refreshAndGetTime()
            lastTime = currentTime.toLocalTime()
            GameController.addDialog(lastTime.format(), this)
            return
        } else {
            GameController.addDialog("Cannot tell the time when its dark", this)
        }
        isCounting = true
    }
}