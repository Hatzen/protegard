package org.example.model.scenario

import org.example.model.scenario.rooms.castle.*
import org.example.model.scenario.rooms.village.VillageEntry

object Rooms {
    val villageEntry = VillageEntry()
    val villageHospital = VillageEntry()
    val castleEntry = CastleEntry()
    val attic = Attic()
    val basement = Basement()
    val bathroom = Bathroom()
    val chapel = Chapel()
    val diningRoom = DiningRoom()
    val hall = Hall()
    val kitchen = Kitchen()
    val library = Library()
    val lobbyRoom = LobbyRoom()
    val pantry = Pantry()
    val sleepingroom = Sleepingroom()
    val secretStudyroom = SecretStudyroom()
    val studyroom = Studyroom()
    val towerRightSideOutside = TowerRightSideOutside()
    val utilityRoom = UtilityRoom()


    val all = listOf(
        villageEntry,
        castleEntry,
        attic,
        basement,
        bathroom,
        chapel,
        diningRoom,
        hall,
        kitchen,
        library,
        lobbyRoom,
        pantry,
        secretStudyroom,
        studyroom,
        towerRightSideOutside, utilityRoom, sleepingroom
    )


    fun init() {
        all.forEach { it.initConnections() }
    }
}