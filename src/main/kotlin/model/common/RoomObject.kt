package org.example.model.common

import org.example.model.interfaces.Identifieable
import org.example.model.interfaces.Interactable

// Like Shelf or an item itself?
abstract class RoomObject(override val fullname: String) : Interactable, Identifieable