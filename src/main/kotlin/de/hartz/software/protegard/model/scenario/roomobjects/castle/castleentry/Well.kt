package de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.controller.LoggerUtil
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.common.environment.CountDownCallback
import de.hartz.software.protegard.model.common.environment.CountDownCallbackIds
import de.hartz.software.protegard.model.common.environment.CountDownTimer
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Items

class Well : RoomObject("Well") {
    override fun interact() {
        val inventory = Characters.MAIN_CHARACTER.inventory
        if (Milestone.WELL_CLEANED.reached) {
            GameController.addDialog("The water is clean, you can see the ground now.", Characters.NARRATOR)
            val alreadyGained = Characters.MAIN_CHARACTER.inventory.contains(Items.rareCoin2)
            if (!alreadyGained) {
                GameController.addDialog("Something is there on the ground.. An uncommon coin.", Characters.NARRATOR)
                Characters.MAIN_CHARACTER.inventory.add(Items.rareCoin2)
                Characters.ASTRID_HANSEN_LADY_OF_THE_CASTLE.addGrantKeyIfNeeded()
            }
        } else {
            if (inventory.contains(Items.salt) && inventory.contains(Items.brush)) {
                inventory.remove(Items.salt)
                inventory.contains(Items.brush)
                GameController.addDialog(
                    "So the salt should remove the remaining algae. Tomorrow the water should be clear.",
                    Characters.NARRATOR
                )
                CountDownTimer.callbacks.add(CountDownCallback({
                    GameController.gamestate.setReached(Milestone.WELL_CLEANED)
                    LoggerUtil.logger.debug { "The well is clean now." }
                    val janitor = Characters.KNUT_JORGENSEN_A_JANITOR
                    janitor.addDialog(janitor.WELL_CLEANED)
                }, 10, CountDownCallbackIds.WELL_CLEANING))
            } else {
                GameController.addDialog(
                    "The water could be cleaned. You better not stick your fingers in it.",
                    Characters.NARRATOR
                )
            }
        }
    }
}