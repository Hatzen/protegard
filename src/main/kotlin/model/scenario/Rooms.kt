package org.example.model.scenario

import org.example.model.scenario.rooms.CastleEntry
import org.example.model.scenario.rooms.VillageEntry

object Rooms {
    val villageEntry = VillageEntry()
    val castleEntry = CastleEntry()

    val all = listOf(villageEntry, castleEntry)


    fun init() {
        all.forEach { it.initConnections() }
    }
}