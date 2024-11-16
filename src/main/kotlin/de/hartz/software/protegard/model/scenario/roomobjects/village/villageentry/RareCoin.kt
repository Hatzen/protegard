package de.hartz.software.protegard.model.scenario.roomobjects.village.villageentry

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Items
import de.hartz.software.protegard.model.scenario.Rooms

class RareCoin : RoomObject("Rare Coin") {
    override fun interact() {
        GameController.addDialog("You picked up a coin that looks uncommon", Characters.NARRATOR)
        Rooms.villageEntry.objects.remove(this)
        Characters.MAIN_CHARACTER.inventory.add(Items.rareCoin)
    }

    override fun preconditionToIdentify(): Boolean {
        return Items.lighter.isBurning
    }
}