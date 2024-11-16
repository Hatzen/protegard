package de.hartz.software.protegard.model.scenario

import de.hartz.software.protegard.controller.GameController

class Scenario {
    fun firstIntro() {
        GameController.addDialog(Characters.NARRATOR.DIALOG_ENTRY, Characters.NARRATOR)
        Rooms.villageEntry.people.add(Characters.TAXI_DRIVER)
        GameController.startDialog(Characters.MAIN_CHARACTER, Characters.TAXI_DRIVER)
    }
}