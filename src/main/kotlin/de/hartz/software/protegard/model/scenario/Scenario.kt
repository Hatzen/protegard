package de.hartz.software.protegard.model.scenario

import de.hartz.software.protegard.controller.GameController

class Scenario {
    fun firstIntro() {
        GameController.addDialog(Characters.NARRATOR.DIALOG_ENTRY, Characters.NARRATOR)
        GameController.startDialog(Characters.MAIN_CHARACTER, Characters.TAXI_DRIVER)
    }
}