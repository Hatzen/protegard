package de.hartz.software.protegard.model.scenario.characters.side

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.scenario.Rooms


/**
 * (Hausmeister): Ein alter, mürrischer Mann, der schon sein ganzes Leben im Schloss gearbeitet hat. Er weiß viel, spricht aber wenig.
 */
class KnutJorgensenAJanitor : Character("Knut Jørgensen", Rooms.basement) {


    val CLEAN_THE_WELL = Dialog(
        """
                May i help you somehow?
                """.trimIndent(),
        Dialog(
            """
                    Take a brush and some salt and clean the well.
                    """.trimIndent(),
            Dialog(
                """
                    You dont like to talk too much, do you? ... Ok, i will see what i can archieve.
                    """.trimIndent(),
                target = this,
            ),
            source = this,
        ),
        target = this,
        onlyOnce = true
    )
    val HMM =
        Dialog(
            """
                Hmm.
                """.trimIndent(),
            source = this
        )
    
    val WELL_CLEANED = Dialog(
        """
            I cleaned the well for you, you can now see the ground again. Isnt it impressive?
                    """.trimIndent(),
        HMM,
        callback = { changeTrust(10) },
        target = this,
    )

    init {
        dialogs = Dialog(
            """
            Excuse me, are you the janitor?
            """.trimIndent(),
            HMM,
            target = this,
            onlyOnce = true
        )
    }

}
