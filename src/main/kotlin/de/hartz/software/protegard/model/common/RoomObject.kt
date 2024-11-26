package de.hartz.software.protegard.model.common

import de.hartz.software.protegard.model.interfaces.Identifieable
import de.hartz.software.protegard.model.interfaces.Interactable

// Like Shelf or an item itself?
abstract class RoomObject(override val fullname: String) : Interactable, Identifieable {

    open fun interact(items: Item) {

    }

}