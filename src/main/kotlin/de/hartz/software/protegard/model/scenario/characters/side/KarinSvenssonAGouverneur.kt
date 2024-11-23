package de.hartz.software.protegard.model.scenario.characters.side

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.scenario.Rooms

/**
 * (Gouvernante): Sie kümmert sich um das Personal des Schlosses und versucht, die Ordnung aufrechtzuerhalten, auch wenn die Ereignisse eskalieren.
 */
class KarinSvenssonAGouverneur : Character("Karin Svensson", Rooms.hall) {
    init {
        dialogs = Dialog(
            """
            Hello mister Lindgren, my name is Miss Svensson i am the gouveneur of this castle, i take care of all administrativ tasks within the castle. I heard Sir Hansen invited you, so welcome.
            """.trimIndent(),
            Dialog(
                """
                Nice to get in contact Miss Svensson.
                """.trimIndent(),
                target = this,
            ),
            source = this,
            // TODO :This will delete all dialogs added afterwards.. quite dangerous..
            //  if first "dialogs" is an OnlyOnce We could queue them in a list, and add them afterwards.
            onlyOnce = true
        )
    }

    val COOK_US_SOMETHING = Dialog(
        """
                Before Sir Hansen died, he told me about your tasty pasta, might you want to cook something for us? If so feel free to use the stoven.
                """.trimIndent(),
        mutableListOf(
            Dialog(
                """
                    Yes sure.
                    """.trimIndent(),
                target = this,
            ),
            Dialog(
                """
                    No, i dont think we have the matching ingredients here.
                    """.trimIndent(),
                target = this,
            ),
        ),
        source = this,
        onlyOnce = true
    )
}