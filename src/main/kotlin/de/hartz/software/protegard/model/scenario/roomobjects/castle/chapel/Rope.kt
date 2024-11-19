package de.hartz.software.protegard.model.scenario.roomobjects.castle.chapel

import de.hartz.software.protegard.controller.DialogController
import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.common.environment.CountDownCallback
import de.hartz.software.protegard.model.common.environment.CountDownCallbackIds.RING_THE_BELLS_GUARD_ANNOYED
import de.hartz.software.protegard.model.common.environment.CountDownTimer
import de.hartz.software.protegard.model.scenario.Characters

class Rope : RoomObject("Rope") {
    override fun interact() {
        GameController.addDialog(
            "A rope hanging from the ceiling. You pull on it..  The bells start ringing.",
            Characters.NARRATOR
        )
        CountDownTimer.callbacks.add(CountDownCallback({
            CountDownTimer.callbacks.remove(this)
            val dialog = Dialog(
                "Hey what are you doing there!? The bells are old and might get defect, stop it now.",
                Dialog(
                    "Oh sorry i didnt knew what the rope is for.",
                    Dialog(
                        "Yeah then maybe dont touch it. *further angry mumbeling while he leaves the chapel*",
                        source = Characters.AKSEL_BRANDT_THE_GUARD,
                    ),
                    target = Characters.AKSEL_BRANDT_THE_GUARD,
                ),
                source = Characters.AKSEL_BRANDT_THE_GUARD
            )
            DialogController(dialog, GameController.view)
        }, 3, RING_THE_BELLS_GUARD_ANNOYED))
    }
}