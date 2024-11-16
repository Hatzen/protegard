package de.hartz.software.protegard.model.common

import de.hartz.software.protegard.model.interfaces.Identifieable
import de.hartz.software.protegard.model.interfaces.Interactable

open class Character(
    final override var fullname: String,
    currentRoom: Room,
    var attributes: Attributes = Attributes(),
    var dialogs: Dialog? = null,
    var inventory: MutableList<Item> = mutableListOf(),
    var alive: Boolean = true
) : Interactable, Identifieable {

    var currentRoom = currentRoom
        private set

    val firstName: String
    val lastName: String

    init {
        firstName = fullname.split(" ").first()
        lastName = fullname.split(" ").last()
        gotoRoom(currentRoom)
    }

    fun gotoRoom(room: Room) {
        currentRoom.people.remove(this)
        currentRoom = room
        currentRoom.people.add(this)
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