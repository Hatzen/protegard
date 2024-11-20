package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.controller.GameController.addDialog
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Items
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Studyroom : Room("Studyroom") {
    override fun initConnections() {
        connections.add(RoomConnection("enter hall", Rooms.hall))
        // TODO: add when solving a riddle.
        connections.add(object: RoomConnection("enter unknown area", Rooms.secretStudyroom) {
            override fun preconditionToIdentify(): Boolean {
                return Milestone.SECRET_STUDY_ROOM_OPENED.reached
            }
        })
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.desk,
                RoomObjects.keyhole
            ))
    }

    override fun onEnter(character: Character?) {
    }

    fun handleCanEnter(): String? {
        val hasKey = Characters.MAIN_CHARACTER.inventory.contains(Items.studyRoomKey)
        if (!hasKey) {
            if (!Milestone.TRIED_TO_ACCESS_STUDY_ROOM.reached) {
                GameController.gamestate.setReached(Milestone.TRIED_TO_ACCESS_STUDY_ROOM)
                val lady = Characters.ASTRID_HANSEN_LADY_OF_THE_CASTLE

                /*
                , but do not lose it!
                lady.addDialog(
                    Dialog(
                        "Excuse me Miss, i just tried to have a look at Sir Hansens last work to fulfill his last will, but the studyroom seems to be locked. May you know who can provide a key?",
                        Dialog(
                            "Sure this is my castle so i decide where the people may or may not go. As my husband probably want you to have a look at his research documents i will grant you the key. But first you can prove us that your worth his results. " +
                                    "Help a little in the castle, the i will grant you access.",
                            Dialog(
                                "Oh nice, i am glad to here, thank you miss.",
                                target = lady,
                            ),
                            callback = {
                                Characters.MAIN_CHARACTER.inventory.add(Items.studyRoomKey)
                            },
                            source = lady
                        ),
                        target = lady
                    )

                )
                 */

                lady.addDialog(
                    Characters.ASTRID_HANSEN_LADY_OF_THE_CASTLE.DENIED_STUDY_ROOM_KEY

                )
            }
            return "The door is locked the key is needed, probably i have to look for it or ask someone."
        }
        return null
    }
}
