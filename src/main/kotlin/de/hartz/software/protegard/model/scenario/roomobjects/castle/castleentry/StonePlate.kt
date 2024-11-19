package de.hartz.software.protegard.model.scenario.roomobjects.castle.castleentry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters

class StonePlate : RoomObject("Well") {
    override fun interact() {
        val solution = "„Þetta hús er reist til heiðurs Guð konungsins“, means „This house is built in honor of God the King.“)."
        if (Milestone.CASTLE_CAN_READ_STONE_PLATE.reached) {
            // "ᛒᛁᛏᛦ᛬ᛅᛁᛋ᛬ᛋᛁᛏ᛬ („Bitr aiss sit“) – „Geh stark deinen Weg“..",
            // „Þetta hús er reist til heiðurs Guð konungsins“ („Dieses Haus ist zu Ehren des Königs Gottes errichtet“).
            GameController.addDialog(
                solution,
                Characters.NARRATOR
            )
        } else {
            GameController.addDialog(
                "I cannot read this, it must be an old dialect.",
                Characters.NARRATOR
            )

            if (!Milestone.CASTLE_CANT_READ_STONE_PLATE.reached) {
                val historian = Characters.INGRID_STJERNBERG_A_HISTORIAN
                val dialog = Dialog(
                    "Can you tell me what is written on the stone plate at the castle entry?",
                    Dialog(
                        "Oh for sure, it is my domain so if i could not tell you this i would be a bad historian.",
                        Dialog(
                            "Sooo... whats the meaning?",
                            Dialog(
                                "Ah sure. The text is like 632 years old, written in the old norwegian dialects the used then. It says $solution, in the old days god was not a single god so it should probably mean the creator of the world.",
                                source = historian,
                                callback = {
                                    GameController.gamestate.setReached(Milestone.CASTLE_CAN_READ_STONE_PLATE)
                                }
                            ),
                            target = historian
                        ),
                        source = historian
                    ),
                    target = historian,
                    onlyOnce = true
                )
                historian.addDialog(dialog)
                GameController.gamestate.setReached(Milestone.CASTLE_CANT_READ_STONE_PLATE)
            }
        }
    }
}