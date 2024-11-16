package de.hartz.software.protegard.model.common

import de.hartz.software.protegard.model.interfaces.Identifieable
import de.hartz.software.protegard.model.interfaces.Interactable

open class Item(
    override val fullname: String
) : Interactable, Identifieable {

    val actions = mutableMapOf<String, () -> Unit>()

    override fun interact() {
    }

    open fun combine(item: Item) {

    }
}