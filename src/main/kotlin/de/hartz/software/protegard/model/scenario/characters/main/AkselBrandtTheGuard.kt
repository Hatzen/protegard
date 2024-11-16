package de.hartz.software.protegard.model.scenario.characters.main

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Dialog
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Rooms

/**
 * (Ex-Soldat, Wachmann des Schlosses):
 * Ein ehemaliger Soldat, der nach dem Ersten Weltkrieg eine Anstellung als Sicherheitschef des Schlosses gefunden hat. Er ist hart und pragmatisch, aber innerlich geplagt von Albträumen. Er vertraut der Wissenschaft nicht und glaubt, dass Gewalt der einzige Weg ist, die drohende Katastrophe zu verhindern.
 */
class AkselBrandtTheGuard : Character("Aksel Brandt", Rooms.castleEntry) {
    init {
        dialogs = getInitialDialog()
    }

    fun getInitialDialog(): Dialog {
        val setChapter1Dialogs = { this.dialogs = getChapter1Dialogs() }
        val acceptedResponse = mutableListOf(
            Dialog(
                "Alright you seem to be sane. You can come in.",
                getChapter1Dialogs(),
                callback = setChapter1Dialogs,
                source = this
            )
        )
        val simpleResponse =
            Dialog(
                "Here is Jonas Lindgren, i got invited by lord hansen.",
                acceptedResponse,
                target = this
            )

        return Dialog(
            """
            Who is there at night? Identify yourself and stay away!
            """.trimIndent(),
            mutableListOf(
                simpleResponse,
                Dialog(
                    "A traveler i need to stay the night, who is asking?",
                    Dialog(
                        "My name is Aksel Brandt, lady Hansen instructed me to care about suspicious people since there are incidents in the village asgardson nearby.",
                        simpleResponse,
                        source = this,
                    ),
                    target = this
                ),
                Dialog("No one tells me where to go, get out of the way.",
                    target = this,
                    callback = {
                    GameController.addDialog(
                        "The guard draws the gun and fires a few shots with trained precision, Jonas falls to the ground, the world becomes dark..",
                        Characters.NARRATOR
                    )
                    GameController.exit()
                })
            ),
            source = this,
            onlyOnce = true
        )
    }

    fun getChapter1Dialogs(): Dialog {

        val aboutTheCastle = Dialog(
            """
           The castle seems old, can you tell me a bit about its history?
            """.trimIndent(),
            Dialog(
                "I am not a tourist guide, i work here for only a year and am not interested in history. The only thing i know is that the castle is older than everyone living within.",
                Dialog(
                    "How cant you be interested in these old structures, but fine lets talk about another topic.",
                    target = this
                ),
                source = this
            ),
            target = this
        )

        val aboutYou = Dialog(
            """
           You look like a professional guard, who are you, whats your story?
            """.trimIndent(),
            Dialog(
                "I were in the army and i am trained for extreme situations, i dont want to tell you me story, the only things you have to know is that you need to follow my instructions in time.",
                source = this
            ),
            target = this
        )


        val initial = Dialog(
            """
           The lady is probably inside in the lobby. Or do you want anything else?
            """.trimIndent(),
            mutableListOf(
                aboutTheCastle,
                aboutYou
            ),
            source = this
        )

        return initial
    }
}