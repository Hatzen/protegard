package org.example.model

import jdk.jfr.Percentage
import org.example.model.interfaces.Identifieable
import org.example.model.interfaces.Interactable

open class Character(
    override var name: String,

    var attributes: Attributes,
    var currentRoom: Room,
    var dialogs: List<Dialog> = listOf(),
    var inventory: List<Item> = listOf(),
    var alive: Boolean = true
): Interactable, Identifieable {

    var trust: Map<Character, Int> = mapOf()

    override fun interact() {
    }

}

class Attributes {
    var strength: Int = 0
    var wisdome: Int = 0
}