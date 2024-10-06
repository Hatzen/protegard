package org.example.model.scenario.items

import org.example.controller.GameController
import org.example.model.Hue
import org.example.model.Item

class Watch: Item("Watch") {

    override fun interact() {
        if (GameController.environment.hue.value >= Hue.OK.value) {
            // TODO: Tell the time
            return
        }
        GameController.addDialog("Cannot tell the time when its dark", this)
    }
}