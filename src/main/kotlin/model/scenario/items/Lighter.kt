package org.example.model.scenario.items

import org.example.model.Item

class Lighter : Item("Lighter") {

    var isEmpty = false
    var isBurning = true


    override fun interact() {
        // TODO: Tell the time
    }
}