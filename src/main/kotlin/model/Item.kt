package org.example.model

import org.example.model.interfaces.Identifieable
import org.example.model.interfaces.Interactable

open class Item(
    override val name: String
): Interactable, Identifieable {
    override fun interact() {
    }

    open fun combine(item: Item) {

    }
}