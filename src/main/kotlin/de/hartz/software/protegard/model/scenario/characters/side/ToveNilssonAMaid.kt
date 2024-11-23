package de.hartz.software.protegard.model.scenario.characters.side

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Rooms

/**
 * (Küchenmädchen): Eine stille Dienerin, die mehr über das Schloss weiß, als sie preisgibt.
 */
class ToveNilssonAMaid : Character("Tove Nilsson", Rooms.kitchen) {

    val KILL_THE_MOUSE = Dialog(
        """
                Hello Tove, i would like to help you! Is there anything i can do for you?
                """.trimIndent(),
        Dialog(
            """
                    Thats a quite uncommon thing, but indeed there is something, i am afraid of rats and some where in the kitchen or pantry there is some. Can you please get rid of it?
                    """.trimIndent(),
            Dialog(
                """
                    Consider it as already done.
                    """.trimIndent(),
                callback = { GameController.gamestate.setReached(Milestone.FIND_MOUSE) },
                target = this,
            ),
            source = this,
        ),
        target = this,
        onlyOnce = true
    )

    val MOUSE_DEAD = Dialog(
        """
            The mouse is dead.
                    """.trimIndent(),
        callback = { changeTrust(10) },
        target = this,
    )

    init {
        dialogs = Dialog(
            """
            Hello Sir, my name is Tove. Nice to meet you. How can i help you?
            """.trimIndent(),
            Dialog(
                """
                Hello Tove, my name is Jonas. I am fine. thank you.
                """.trimIndent(),
                target = this,
                onlyOnce = true
            ),
            source = this
        )
    }

}