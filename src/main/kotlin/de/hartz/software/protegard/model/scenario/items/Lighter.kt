package de.hartz.software.protegard.model.scenario.items

import de.hartz.software.protegard.model.common.Item

class Lighter : Item("Lighter") {

    var isEmpty = false
    var isBurning = true


    override fun interact() {
        // TODO: Tell the time
    }
}