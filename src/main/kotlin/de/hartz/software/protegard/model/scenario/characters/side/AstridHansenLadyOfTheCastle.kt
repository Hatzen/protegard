package de.hartz.software.protegard.model.scenario.characters.side

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Items
import de.hartz.software.protegard.model.scenario.Rooms

/**
 * (Schlossherrin): Die mysteriöse Besitzerin des Schlosses, die seltsam abwesend wirkt und von der Bedrohung weiß.
 */
class AstridHansenLadyOfTheCastle : Character("Astrid Hansen", Rooms.lobbyRoom) {

    val FIRST_MEET = Dialog(
        "Welcome to my castle, are you " + Characters.MAIN_CHARACTER.fullname + "?",
        Dialog(
            "Yes. So you are the Lady of the Castle, Lady Hansen?",
            Dialog(
                "Yes as well. My husband told me he wrote with you and that you will probably come around. But i am sorry to inform you that Sir Hansen died three weeks ago." +
                        "We tried to send after you but obviously it did not reach you in time. How are you? Did you travel without bigger issues?",
                Dialog(
                    "Dont worry about me, my journey went well. I am sorry to hear your husband passed away. If i can provide anything please say so.",
                    Dialog(
                        "That is nice. But i am fine as well, we are old already and expected to die sometime. I dont want to annoy you with my story so far, you can take the bedroom in the first floor, " +
                                "your room is the one on the left of the painting of Sir Hansen. If you need something feel free to ask, but in general be carful this castle is old and some things are not in their best condition." +
                                "So have a good night at first. ",
                        callback = { GameController.gamestate.setReached(Milestone.SLEEPING_ROOM_GRANTED) },
                        source = this
                    ),
                    target = this
                ),
                source = this
            ),
            target = this
        ),
        source = this
    )

    val DENIED_STUDY_ROOM_KEY: Dialog by lazy {
        val fineAnswer = Dialog(
            "Fine, then ask me again if you have proven yourself.",
            source = this,
            callback = { initMissions() }
        )

        Dialog(
            "Excuse me Miss, i just tried to have a look at Sir Hansens last work to fulfill his last will, but the studyroom seems to be locked. May you know who can provide a key?",
            Dialog(
                "Sure this is my castle so i decide where the people may or may not go. As my husband probably want you to have a look at his research documents i will grant you the key. But first you can prove us that your worth his results. " +
                        "Help a little in the castle, the i will grant you access.",
                mutableListOf(
                    Dialog(
                        "Without hesitation i like to help.",
                        fineAnswer,
                        target = this,
                        callback = { changeTrust(10) }
                    ),
                    Dialog(
                        "I rather not, i had a long trip and i really want to hurry up, but i guess i have no choice.",
                        fineAnswer,
                        target = this,
                    ),
                    Dialog(
                        "I have no time for such bullshit give me the key or i come for it.",
                        Dialog(
                            "Thats rude, okay, so here you have the key, hurry up and leave my ground as fast as you can, i dont like to share my time with street dogs. But dont you dare to lose it!",
                            source = this,
                            callback = {
                                changeTrust(-20)
                                initMissions()
                                Characters.MAIN_CHARACTER.inventory.add(Items.studyRoomKey)
                                GameController.gamestate.setReached(Milestone.MISSION_TO_GET_KEY_TO_STUDY_ROOM_DONE)
                            }
                        ),
                        target = this
                    )
                ),
                source = this
            ),
            target = this,
            onlyOnce = true
        )
    }

    private fun initMissions() {
        // TODO: how about charcter.addDialog { it.attributDialog } would be shorter..
        val maid = Characters.TOVE_NILSSON_A_MAID
        maid.addDialog(maid.KILL_THE_MOUSE)

        val janitor = Characters.KNUT_JORGENSEN_A_JANITOR
        janitor.addDialog(janitor.CLEAN_THE_WELL)

        val gouvener = Characters.KARIN_SVENSSON_A_GOUVERNEUR
        gouvener.addDialog(gouvener.COOK_US_SOMETHING)
    }

    fun addGrantKeyIfNeeded() {
        if (Milestone.MISSION_TO_GET_KEY_TO_STUDY_ROOM_DONE.reached) {
            return
        }
        val GRANTED_STUDY_ROOM_KEY =
            Dialog(
                "Nice to see you, i already heard of your eaver actions. Here is your key.",
                Dialog(
                    "Thanks miss.",
                    callback = {
                        Characters.MAIN_CHARACTER.inventory.add(Items.studyRoomKey)
                        GameController.gamestate.setReached(Milestone.MISSION_TO_GET_KEY_TO_STUDY_ROOM_DONE)
                    },
                    target = this
                ),
                source = this
            )
        this.addDialog(GRANTED_STUDY_ROOM_KEY)
    }

    init {
        dialogs = FIRST_MEET
    }

}