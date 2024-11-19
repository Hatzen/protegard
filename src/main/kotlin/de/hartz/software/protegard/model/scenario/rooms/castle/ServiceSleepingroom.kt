package de.hartz.software.protegard.model.scenario.rooms.castle

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.Rooms

// Never will be entered, just to keep some secret rooms.
class ServiceSleepingroom : Room("Service Sleepingroom") {
    override fun initConnections() {
    }

    override fun initRoomObjects() {

    }

    override fun onEnter(character: Character?) {
    }
}
