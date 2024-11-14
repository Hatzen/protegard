package org.example.controller

import org.example.controller.generative.ChatGPTAdventure
import org.example.controller.generative.ContextAnswerController
import org.example.controller.generative.RandomAnswerController
import org.example.controller.generative.setup.SetupHelper
import org.example.model.common.Character
import org.example.model.common.Item
import org.example.model.common.RoomConnection
import org.example.model.common.RoomObject
import org.example.model.common.environment.Environment
import org.example.model.interfaces.Identifieable
import org.example.model.scenario.Characters
import org.example.model.scenario.Rooms
import org.example.model.scenario.Scenario
import org.example.model.settings.Settings
import org.example.view.text.TextIO
import kotlin.system.exitProcess

object GameController {

    lateinit var view: IView
    lateinit var randomAnswerController: RandomAnswerController
    lateinit var contextAnswerController: ContextAnswerController

    // TODO: This usually depends on the room we are currently in..
    lateinit var environment: Environment

    fun init() {
        SetupHelper().startOlama()

        Rooms.init()
        environment = Environment()
        randomAnswerController = RandomAnswerController(ChatGPTAdventure(Settings, environment))
        contextAnswerController = ContextAnswerController(ChatGPTAdventure(Settings, environment))

        this.view = TextIO(ChatGPTAdventure(Settings, environment))
        view.start()

        // TODO: save / load
        Scenario().firstIntro()
    }

    fun goto(connection: RoomConnection, character: Character = Characters.MAIN_CHARACTER) {
        if (!Characters.MAIN_CHARACTER.currentRoom.canLeave()) {
            return
        }
        if (connection.canTravel()) {
            character.currentRoom = connection.toRoom
            connection.toRoom.onEnter(character)
        } else {
            // TODO: travel message may need to contain the character or identifieable which shows the message
            addDialog(connection.travelMessage()!!, Characters.NARRATOR)
        }
    }

    fun startDialog(to: Character, initator: Character = Characters.MAIN_CHARACTER) {
        to.interact()
        val dialog = to.dialogs
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

    fun lookAround(): List<Identifieable> {
        val list: List<Identifieable> = getAllRoomConnections().map { it.toRoom }
            .plus(getAllRoomObjects())
            .plus(getAllPeople())
            .filter { it.preconditionToIdentify() }

        return list
    }

    fun exit() {
        exitProcess(0)
    }

    // TODO: Needs persistence concept.. Serializable and statics will lead to errors..
    fun save() {

    }

    fun load() {}


}