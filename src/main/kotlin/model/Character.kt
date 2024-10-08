package org.example.model

import jdk.jfr.Percentage
import org.example.model.interfaces.Identifieable
import org.example.model.interfaces.Interactable

open class Character(
    override var name: String,

    var currentRoom: Room,
    var attributes: Attributes = Attributes(),
    var dialogs: MutableList<Dialog> = mutableListOf(),
    var inventory: MutableList<Item> = mutableListOf(),
    var alive: Boolean = true
): Interactable, Identifieable {

    var trust: Map<Character, Int> = mapOf()

    override fun interact() {
    }

}

class Attributes {
    var strength: Int = 0
    var wisdome: Int = 0
    // TODO: Energy level, walking or switching room costs energy, needs to sleep when 0
    var currentEnergyLevel = 100
}