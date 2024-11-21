package de.hartz.software.protegard.model.common

import de.hartz.software.protegard.model.interfaces.Identifieable

abstract class Room(
    override var fullname: String,
    var connections: MutableSet<RoomConnection> = mutableSetOf(),
    var people: MutableSet<Character> = mutableSetOf(),
    var objects: MutableSet<RoomObject> = mutableSetOf()
) : Identifieable {

    abstract fun initConnections()
    abstract fun initRoomObjects()

    open fun onEnter(character: Character? = null) {

    }

    open fun onLeave() {

    }

    open fun canLeave() = true
}

open class RoomConnection(
    override val fullname: String, val toRoom: Room,
    val travelMessage: () -> String? = { null }
) : Identifieable {
    open fun canTravel(): Boolean {
        return travelMessage() == null
    }
}

