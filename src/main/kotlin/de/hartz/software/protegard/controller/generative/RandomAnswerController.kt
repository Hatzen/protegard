package de.hartz.software.protegard.controller.generative

import de.hartz.software.protegard.controller.generative.content.ChatGPTAdventure
import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.Room
import de.hartz.software.protegard.model.common.RoomObject
import de.hartz.software.protegard.model.interfaces.Identifieable
import java.time.LocalDate

class RandomAnswerController(val chatGPTAdventure: ChatGPTAdventure) {


    fun getDescriptionForObject(prompt: String): String {
        return chatGPTAdventure.getNarratorBasedContent(prompt)
    }

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

    fun getRandomAnswerForLookingAround(
        currentRoom: Identifieable,
        roomConnections: List<Room>,
        persons: List<Character>,
        roomObjects: List<RoomObject>
    ): String {
        val currentRoom = currentRoom.fullname
        val rooms = roomConnections.map { it.fullname }.joinToString(",") { "'$it'" }
        val persons = persons.map { it.fullname }.joinToString(",") { "'$it'" }
        val objects = roomObjects.map { it.fullname }.joinToString(",") { "'$it'" }
        return chatGPTAdventure.generateStoryIndependentStuff(
            // Generate an inner monologue for someone who looks around in '$currentRoom'. Describe the atmosphere a little and generate an illustration so the text seems immersive.
            //
            """
                Generate text for '$currentRoom'. 
                It must describe following entities. Every entity must be listed exact once and exactly that way (no translation or changes) but enclosed by "".
                 The entities have 3 categories rooms, persons, objects. Where rooms are just places someone can go. Persons are people one could speak to and objects can be anything like tables or things human can interact with.
                 You can move to following rooms: $rooms . 
                 You can interact with following persons: $persons .
                 You can interact with following objects: $objects .
                 
                 An Example for a room would be:
                 You can move to following rooms: 'street to castle'
                 
                 The 'street to castle' looks far and is not in a good shape. There is plenty of nature around, but as it is dark you cannot see where it ends.
               
               
                 An Example for a person would be:
                 You can interact with following persons: 'Dummy Character'
                 
                 'Dummy Character' seems to be thinking about something. She looks pretty and her brown hair fits the trees outside.
               
                 An Example for a object would be:
                 You can interact with following objects: 'Table'
                 
                 The 'Table' over there looks like it was cheap in the beginning but is just hardly used probably over the last 10 years.
                 
                """.trimIndent()
            /*

                     Do consider only the entities enclosed with ''.
                     Do not interprete spaces in '' as new entities, the whole text enclosed in '' is one single entity which must be repeated exactly like this.
                     do not use any other entities you might know from previous conversations.
             */

        )
    }


}