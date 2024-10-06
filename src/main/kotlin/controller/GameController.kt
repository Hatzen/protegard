package org.example.controller

import org.example.model.Character
import org.example.model.Environment
import org.example.model.Item
import org.example.model.RoomConnection
import org.example.model.interfaces.Identifieable
import org.example.model.scenario.Characters
import kotlin.system.exitProcess

object GameController {


    lateinit var view: IView

    // TODO: This usually depends on the room we are currently in..
    lateinit var environment: Environment

    fun goto(connection: RoomConnection, character: Character = Characters.MAIN_CHARACTER) {
        if (connection.canTravel()) {
            character.currentRoom = connection.toRoom
            connection.toRoom.onEnter(character)
        } else {
            // TODO: travel message may need to contain the character or identifieable which shows the message
            addDialog(connection.travelMessage()!!, Characters.NARRATOR)
        }
    }

    fun addDialog(text: String, source: Identifieable) {
        view.addDialog(text, source)
    }

    fun getAllRoomConnections(): List<RoomConnection> {
        return Characters.MAIN_CHARACTER.currentRoom.connections
    }

    fun getInventory(): List<Item> {
        return Characters.MAIN_CHARACTER.inventory
    }

    fun listPeople(): List<Character> {
        return Characters.MAIN_CHARACTER.currentRoom.people
    }

    fun exit() {
        exitProcess(0)
    }

    // TODO: Needs persistence concept.. Serializable and statics will lead to errors..
    fun save() {

    }
    fun load() {}



}