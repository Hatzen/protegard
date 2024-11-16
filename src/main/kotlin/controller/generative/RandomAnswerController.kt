package org.example.controller.generative

import org.example.model.common.Character
import org.example.model.common.RoomConnection
import org.example.model.common.RoomObject
import org.example.model.interfaces.Identifieable
import java.time.LocalDate

class RandomAnswerController(val chatGPTAdventure: ChatGPTAdventure) {


    fun getRandomNewspaper(date: LocalDate): String {
        return chatGPTAdventure.generateStoryIndependentStuff(
            """
         Give me a random newspaper article from the year $date        
            """.trimIndent()
        )
    }

    fun getRandomAnswerForPeopleWithoutDialog(targetPerson: Identifieable): String {
        return chatGPTAdventure.getNarratorBasedContent(
            """
         Generate a narrator based answer for someone you dont have anything 
         to say for. The person is $targetPerson           
            """.trimIndent()
        )
    }

    /*
    fun getRandomAnswerForLookingAround(room: Identifieable): String {
        return chatGPTAdventure.getNarratorBasedContent(
            """
                Generate an inner monologue for someone who looks around in 
                a $room. It should be quiet meaningless.
                """.trimIndent()
        )
    } */

    fun getRandomAnswerForLookingAround(
        currentRoom: Identifieable,
        roomConnections: List<RoomConnection>,
        persons: List<Character>,
        roomObjects: List<RoomObject>
    ): String {
        val rooms = roomConnections.map { it.fullname }.joinToString { "," }
        val persons = persons.map { it.fullname }.joinToString { "," }
        val objects = roomObjects.map { it.fullname }.joinToString { "," }
        return chatGPTAdventure.getNarratorBasedContent(
            """
                Generate an inner monologue for someone who looks around in $currentRoom. Describe the atmosphere a little and generate an illustration so the text seems immersive.
                It must describe following rooms, persons, items. Every entity must be listed exact once with the exact name but enclosed by "".
                 You can move to following rooms: $rooms . 
                 You can interact with following persons: $persons .
                 You can interact with following objects: $objects .
                """.trimIndent()
        )
    }


}