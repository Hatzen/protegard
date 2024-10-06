package org.example.model

import model.RoomObject
import org.example.model.interfaces.Identifieable
import org.example.model.scenario.Characters
import java.util.function.Predicate

abstract class Room (
    override var name: String,
    var connections: List<RoomConnection> = listOf(),
    var people: List<Character> = listOf(),
    var objects: List<RoomObject> = listOf()
): Identifieable {
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

