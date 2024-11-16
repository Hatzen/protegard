package de.hartz.software.protegard

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Rooms
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream



class UseCaseTest {

    @Disabled
    @Test
    fun testUseCase() {
        GameController.init()
        Characters.MAIN_CHARACTER.gotoRoom(Rooms.castleEntry)

        provideInput("la\n\rx")
        GameController.view.listenForUserInput()
    }

    fun provideInput(data: String) {
        val testIn = ByteArrayInputStream(data.toByteArray())
        System.setIn(testIn)
    }
}