package org.example.model.common

import org.example.model.interfaces.Identifieable
import org.example.model.interfaces.Interactable

open class Item(
    override val fullname: String
) : Interactable, Identifieable {
    override fun interact() {
    }

    open fun combine(item: Item) {

    }
}