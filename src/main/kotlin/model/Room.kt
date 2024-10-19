package org.example.model

import model.RoomObject
import org.example.model.interfaces.Identifieable

abstract class Room(
    override var name: String,
    var connections: MutableList<RoomConnection> = mutableListOf(),
    var people: MutableList<Character> = mutableListOf(),
    var objects: MutableList<RoomObject> = mutableListOf()
) : Identifieable {

    abstract fun initConnections()
    abstract fun initRoomObjects()

    open fun onEnter(character: Character? = null) {

    }

    open fun onLeave() {

    }

    open fun canLeave() = true
}

class RoomConnection(override val name: String, val toRoom: Room, val travelMessage: () -> String? = { null }) :
    Identifieable {
    fun canTravel(): Boolean {
        return travelMessage != null
    }
}

