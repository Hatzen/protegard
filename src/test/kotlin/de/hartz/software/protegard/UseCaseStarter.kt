package de.hartz.software.protegard

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.common.RoomConnection
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Rooms
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream



fun main(args: Array<String>) {
    testUseCase()
}
    fun testUseCase() {
        GameController.init()
        GameController.goto(RoomConnection("Street to castle", Rooms.castleEntry))

        // GameController.view.listenForUserInput()
    }