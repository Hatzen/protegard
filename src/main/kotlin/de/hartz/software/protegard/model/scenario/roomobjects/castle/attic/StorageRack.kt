package de.hartz.software.protegard.model.scenario.roomobjects.castle.attic

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters

class StorageRack : RoomObject("StorageRack") {
    override fun interact() {
        GameController.addDialog(
            "Many shelves with plates, paper and other worthless stuff.",
            Characters.NARRATOR
        )
    }
}