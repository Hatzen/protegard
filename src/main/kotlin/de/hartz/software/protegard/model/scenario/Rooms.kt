package de.hartz.software.protegard.model.scenario

import de.hartz.software.protegard.model.scenario.rooms.Hell
import de.hartz.software.protegard.model.scenario.rooms.castle.*
import de.hartz.software.protegard.model.scenario.rooms.village.VillageEntry

object Rooms {
    // Village
    val villageEntry = VillageEntry()
    val villageHospital = VillageEntry()

    // Castle rooms
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
    val secretStudyroom = SecretStudyroom()
    val studyroom = Studyroom()
    val towerRightSideOutside = TowerRightSideOutside()
    val utilityRoom = UtilityRoom()
    val sleepingroom = Sleepingroom()
    val castleLadySleepingroom = CastleLadySleepingroom()
    val serviceSleepingroom = ServiceSleepingroom()

    // Other.

    val hell = Hell()


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
        all.forEach {
            it.initConnections()
            it.initRoomObjects()
        }
    }
}