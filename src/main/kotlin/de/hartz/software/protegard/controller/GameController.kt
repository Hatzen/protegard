package de.hartz.software.protegard.controller

import de.hartz.software.protegard.controller.generative.ChatGPTAdventure
import de.hartz.software.protegard.controller.generative.ContextAnswerController
import de.hartz.software.protegard.controller.generative.RandomAnswerController
import de.hartz.software.protegard.controller.generative.setup.SetupHelper
import de.hartz.software.protegard.model.common.*
import de.hartz.software.protegard.model.common.environment.Environment
import de.hartz.software.protegard.model.interfaces.Identifieable
import de.hartz.software.protegard.model.milestones.Chapter
import de.hartz.software.protegard.model.scenario.Characters
import de.hartz.software.protegard.model.scenario.Rooms
import de.hartz.software.protegard.model.scenario.Scenario
import de.hartz.software.protegard.model.settings.Settings
import de.hartz.software.protegard.view.text.TextIO
import kotlin.system.exitProcess

object GameController {

    lateinit var view: IView
    lateinit var randomAnswerController: RandomAnswerController
    lateinit var contextAnswerController: ContextAnswerController

    // TODO: This usually depends on the room we are currently in..
    lateinit var environment: Environment
    lateinit var gamestate: Gamestate

    fun init() {

        SetupHelper().startOlama()

        Rooms.init()
        environment = Environment()
        gamestate = Gamestate()
        randomAnswerController = RandomAnswerController(ChatGPTAdventure(Settings, gamestate))
        contextAnswerController = ContextAnswerController(ChatGPTAdventure(Settings, gamestate))

        this.view = TextIO(ChatGPTAdventure(Settings, gamestate))
    }

    fun startGameFromBeginning() {
        // TODO: save / load
        view.startChapter(1)
        Scenario().firstIntro()
        // Must be last line as it will be blocking.
        view.listenForUserInput()
    }

    fun nextChapter() {
        val nextChapter = gamestate.currentChapter.order + 1
        val next = Chapter.entries.find { nextChapter.toShort() == it.order }!!
        gamestate.currentChapter.reached = true
        gamestate.currentChapter = next
        view.startChapter(nextChapter)
    }

    fun getCurrentObjective(): String {
        return ObjectiveController.getCurrentObjective()
    }

    fun goto(connection: RoomConnection, character: Character = Characters.MAIN_CHARACTER) {
        if (!Characters.MAIN_CHARACTER.currentRoom.canLeave()) {
            return
        }
        if (connection.canTravel()) {
            character.gotoRoom(connection.toRoom)
            connection.toRoom.onEnter(character)
        } else {
            // TODO: travel message may need to contain the character or identifieable which shows the message
            addDialog(connection.travelMessage()!!, Characters.NARRATOR)
        }
    }

    fun startDialog(to: Character, initator: Character = Characters.MAIN_CHARACTER) {
        to.interact()

        // Evaluate if this is always useful. Or should we
        val dialog = if (to == Characters.MAIN_CHARACTER) {
            initator.dialogs
        } else {
            to.dialogs
        }

        if (dialog == null) {
            val answer = randomAnswerController.getRandomAnswerForPeopleWithoutDialog(to)
            addDialog(answer, to)
            return
        }
        DialogController(dialog, view)
    }

    fun addDialog(text: String, source: Identifieable) {
        view.addText(text, source)
    }

    fun getAllRoomConnections(): List<RoomConnection> {
        return Characters.MAIN_CHARACTER.currentRoom.connections.filter { it.preconditionToIdentify() }
    }

    fun getAllRoomObjects(): List<RoomObject> {
        return Characters.MAIN_CHARACTER.currentRoom.objects.filter { it.preconditionToIdentify() }
    }

    fun getInventory(): List<Item> {
        return Characters.MAIN_CHARACTER.inventory.filter { it.preconditionToIdentify() }
    }

    fun getAllPeople(): List<Character> {
        return Characters.MAIN_CHARACTER.currentRoom.people.filter { it.preconditionToIdentify() }
    }

    fun lookAround() {
        val response = randomAnswerController.getRandomAnswerForLookingAround(
            Characters.MAIN_CHARACTER.currentRoom,
            // TODO: There could be multiple ways to get to the same room.. but room connections would need to have easier/proper names not sentences.
            getAllRoomConnections().map { it.toRoom },
            getAllPeople(),
            getAllRoomObjects()
        )

        view.addText(response, Characters.NARRATOR)
    }

    fun exit() {
        exitProcess(0)
    }

    // TODO: Needs persistence concept.. Serializable and statics will lead to errors..
    fun save() {

    }

    fun load() {}


}