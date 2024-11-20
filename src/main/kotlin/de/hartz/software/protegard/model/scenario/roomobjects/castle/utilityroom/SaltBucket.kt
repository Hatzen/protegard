package de.hartz.software.protegard.model.scenario.roomobjects.castle.utilityroom

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Items

class SaltBucket : RoomObject("SaltBucket") {
    override fun interact() {
        GameController.addDialog(
            "A bucket of salt to avoid ice and weed.",
            Characters.NARRATOR
        )
        Characters.MAIN_CHARACTER.inventory.add(Items.salt)
    }

    override fun preconditionToIdentify(): Boolean {
        return !Characters.MAIN_CHARACTER.inventory.contains(Items.salt)
    }
}