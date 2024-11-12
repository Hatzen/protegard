package org.example.model.common

import org.example.model.interfaces.Identifieable
import org.example.model.interfaces.Interactable

open class Character(
    final override var fullname: String,

    var currentRoom: Room,
    var attributes: Attributes = Attributes(),
    var dialogs: Dialog? = null,
    var inventory: MutableList<Item> = mutableListOf(),
    var alive: Boolean = true
) : Interactable, Identifieable {
    val firstName: String
    val lastName: String

    init {
        firstName = fullname.split(" ").first()
        lastName = fullname.split(" ").last()
    }

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