package de.hartz.software.protegard.model.scenario.roomobjects.castle.attic

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.common.environment.Environment
import de.hartz.software.protegard.model.scenario.Characters
import kotlin.random.Random

class PileOfNewspaper : RoomObject("PileOfNewspaper") {
    override fun interact() {
        val beforeYears = Random.nextLong(3, 37)
        val month = Random.nextLong(1, 12)
        val day = Random.nextLong(1, 30)
        val randomNewspaperYear = GameController.environment.currentGameDateTime.toLocalDate().minusYears(beforeYears).minusMonths(month).minusDays(day)
        val newspaper = GameController.randomAnswerController.getRandomNewspaper(randomNewspaperYear)
        GameController.addDialog(newspaper, Characters.NARRATOR)
    }
}