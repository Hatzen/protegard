package org.example.model.scenario.roomobjects

import model.RoomObject
import org.example.controller.GameController
import org.example.model.scenario.Characters
import org.example.model.scenario.Items
import org.example.model.scenario.Rooms

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