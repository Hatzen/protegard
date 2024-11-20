package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.milestones.Milestone
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Items
import de.hartz.software.protegard.model.scenario.RoomObjects
import de.hartz.software.protegard.model.scenario.Rooms

class Pantry : Room("Pantry") {
    override fun initConnections() {
        connections.add(RoomConnection("enter kitchen", Rooms.kitchen))
    }

    override fun initRoomObjects() {
        objects.addAll(
            mutableListOf(
                RoomObjects.cheeseWheel,
                RoomObjects.flour,
                RoomObjects.glassJars,
                RoomObjects.weighingScale,
                RoomObjects.woodenBarrel,
                RoomObjects.weights
            )
        )
    }

    override fun onEnter(character: Character?) {
        if (Milestone.FOLLOW_MOUSE.reached && !Milestone.FOUND_MOUSE.reached) {
            GameController.addDialog(
                "You can see the trace of the mouse. Behind the bag there is a tiny whole, when you stabbed into it the mouse came out and you stepped on it. Problem solved! Interesting you found an uncommon coin.",
                Characters.NARRATOR
            )
            Characters.MAIN_CHARACTER.inventory.add(Items.rareCoin3)
            GameController.gamestate.setReached(Milestone.FOUND_MOUSE)
            Characters.ASTRID_HANSEN_LADY_OF_THE_CASTLE.addGrantKeyIfNeeded()
            Characters.TOVE_NILSSON_A_MAID.addDialog(Characters.TOVE_NILSSON_A_MAID.MOUSE_DEAD)
        }
    }
}
