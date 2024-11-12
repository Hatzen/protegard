package org.example.model.scenario.items

import org.example.controller.GameController
import org.example.model.common.Hue
import org.example.model.common.Item

class Watch : Item("Watch") {

    // TODO: wind up the watch, if not done every 2 hours reset it
    override fun interact() {
        if (GameController.environment.hue.value >= Hue.OK.value) {
            // TODO: Tell the time
            return
        }
        GameController.addDialog("Cannot tell the time when its dark", this)
    }
}