package de.hartz.software.protegard.model.scenario.roomobjects.castle.kitchen

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.controller.puzzels.CookingPuzzle
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Items

class FireStoven : RoomObject("FireStoven") {
    override fun interact() {
        GameController.addDialog(
            "A big stoven powered by coal.",
            Characters.NARRATOR
        )
        if (Characters.MAIN_CHARACTER.inventory.contains(Items.coal)) {
            Characters.MAIN_CHARACTER.inventory.remove(Items.coal)
            val result = CookingPuzzle.start(GameController.view)
            val minutes = 2 * 60L
            GameController.environment.passTime(minutes)
            GameController.addDialog("The food is ready, lets ring the dining bell.", Characters.MAIN_CHARACTER)
            var trust = 0
            var text = ""
            when (result) {
                CookingPuzzle.Score.PERFECT -> {
                    trust = 10
                    text = "Wow thats perfect, i dont want to eat anything else again."
                    Characters.ASTRID_HANSEN_LADY_OF_THE_CASTLE.addGrantKeyIfNeeded()
                }

                CookingPuzzle.Score.NICE -> {
                    trust = 5
                    text = "Pretty good."
                    Characters.ASTRID_HANSEN_LADY_OF_THE_CASTLE.addGrantKeyIfNeeded()
                }

                CookingPuzzle.Score.OK -> {
                    text = "You dont cook frequently do you?"
                }

                CookingPuzzle.Score.DISGUSTING -> {
                    trust = -5
                    text = "Ughh. someone shat in my food."
                }
            }
            val gouverner = Characters.KARIN_SVENSSON_A_GOUVERNEUR
            gouverner.changeTrust(trust)
            gouverner.addDialog(
                Dialog(
                    text,
                    source = gouverner,
                    onlyOnce = true
                )
            )
            GameController.startDialog(gouverner)
        }
    }
}