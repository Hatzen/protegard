package org.example.model

import org.example.model.interfaces.Identifieable
import org.example.model.interfaces.Interactable

open class Character(
    override var name: String,

    var attributes: Attributes,
    var currentRoom: Room,

    var inventory: List<Item> = listOf(),
    var alive: Boolean = true
): Interactable, Identifieable {
    override fun interact() {
    }

}

class Attributes {
    var strength: Int = 0
    var wisdome: Int = 0
}