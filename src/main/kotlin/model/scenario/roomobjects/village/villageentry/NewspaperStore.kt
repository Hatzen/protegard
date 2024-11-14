package org.example.model.scenario.roomobjects.village.villageentry

import org.example.controller.GameController
import org.example.model.common.RoomObject
import org.example.model.scenario.Characters
import java.time.LocalDate

class NewspaperStore : RoomObject("Newsstand") {
    private val newspaperCounter = HashMap<LocalDate, List<String>>()
    private val MAX_PER_DAY = 3

    override fun interact() {
        val date = GameController.environment.currentGameDateTime.toLocalDate()
        val list = newspaperCounter.getOrPut(date) { mutableListOf() }
        if (list.size >= MAX_PER_DAY) {
            GameController.addDialog("Nothing new.. " + list.random(), Characters.NARRATOR)
            return
        }
        val text = GameController.randomAnswerController.getRandomNewspaper(date)
        GameController.addDialog("You have a look at the newspaper" + text, Characters.NARRATOR)
    }
}