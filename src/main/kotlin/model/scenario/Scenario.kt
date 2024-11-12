package org.example.model.scenario

import org.example.controller.GameController

class Scenario {
    fun firstIntro() {
        GameController.addDialog(Characters.NARRATOR.DIALOG_ENTRY, Characters.NARRATOR)
        Rooms.villageEntry.people.add(Characters.TAXI_DRIVER)
        GameController.startDialog(Characters.MAIN_CHARACTER, Characters.TAXI_DRIVER)
    }
}