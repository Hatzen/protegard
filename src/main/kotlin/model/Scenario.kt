package org.example.model

import org.example.controller.GameController
import org.example.model.scenario.Characters
import org.example.model.scenario.Rooms

class Scenario {
    fun firstIntro() {
        GameController.addDialog(Characters.NARRATOR.DIALOG_ENTRY, Characters.NARRATOR)
        Rooms.villageEntry.people.add(Characters.TAXI_DRIVER)
        GameController.startDialog(Characters.MAIN_CHARACTER, Characters.TAXI_DRIVER)
    }
}