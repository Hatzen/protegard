package org.example.model

import model.RoomObject
import org.example.model.interfaces.Identifieable
import org.example.model.scenario.Characters
import java.util.function.Predicate

abstract class Room (
    override var name: String,
    var connections: MutableList<RoomConnection> = mutableListOf(),
    var people: MutableList<Character> = mutableListOf(),
    var objects: MutableList<RoomObject> = mutableListOf()
): Identifieable {

    abstract fun initConnections()

    open fun onEnter(character: Character? = null) {

    }

    open fun onLeave() {

    }
}

class RoomConnection(val toRoom: Room, val travelMessage: () -> String? = { null }) {
    fun canTravel(): Boolean {
        return travelMessage != null
    }
}

