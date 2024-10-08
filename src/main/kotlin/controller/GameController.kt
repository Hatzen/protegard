package org.example.controller

import model.RoomObject
import org.example.model.*
import org.example.model.interfaces.Identifieable
import org.example.model.scenario.Characters
import org.example.model.scenario.Items
import org.example.model.scenario.Rooms
import kotlin.system.exitProcess

object GameController {

    // val characters = Characters()
    // val items = Items()
    // val rooms = Rooms()
    // val rooms = Rooms()

    lateinit var view: IView

    // TODO: This usually depends on the room we are currently in..
    lateinit var environment: Environment

    fun init(view: IView) {
        this.view = view
        Rooms.init()

        // TODO: save / load
        Scenario().firstIntro()
    }

    fun goto(connection: RoomConnection, character: Character = Characters.MAIN_CHARACTER) {
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
        // TODO: How to get responses etc.
        to.dialogs
    }

    fun addDialog(text: String, source: Identifieable) {
        view.addDialog(text, source)
    }

    fun getAllRoomConnections(): List<RoomConnection> {
        return Characters.MAIN_CHARACTER.currentRoom.connections
    }

    fun getAllRoomObjects(): List<RoomObject> {
        return Characters.MAIN_CHARACTER.currentRoom.objects
    }

    fun getInventory(): List<Item> {
        return Characters.MAIN_CHARACTER.inventory
    }

    fun getAllPeople(): List<Character> {
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