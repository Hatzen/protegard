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

    var trust: Map<Character, Int> = mapOf()

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

    // TODO: is this really useful? Probably it would be better to have a special Dialog for the start,
    //   so we are sure there is some sort of greeting and then a simple topic selection..
    fun addDialog(dialog: Dialog) {
        val allDialogs = dialogs
        if (allDialogs == null) {
            dialogs = dialog
        } else {
            val answers = allDialogs.answers
            if (answers == null) {
                allDialogs.answers = mutableListOf(dialog)
            } else {
                answers.add(dialog)
            }
        }
    }

    override fun interact() {
    }

}

class Attributes {
    var strength: Int = 0
    var wisdome: Int = 0

    // TODO: Energy level, walking or switching room costs energy, needs to sleep when 0
    var currentEnergyLevel = 100
}