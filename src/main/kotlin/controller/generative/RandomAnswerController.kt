package org.example.controller.generative

import org.example.model.interfaces.Identifieable
import java.util.*

object RandomAnswerController {

    fun getRandomNewspaper(date: Date): String {
        return getRandomAnswerForSeed(
            """
         Give me a random newspaper article from the year $date        
            """.trimIndent()
        )
    }

    fun getRandomAnswerForPeopleWithoutDialog(targetPerson: Identifieable): String {
        return getRandomAnswerForSeed(
            """
         Generate a narrator based answer for someone you dont have anything 
         to say for. The person is $targetPerson           
            """.trimIndent()
        )
    }

    fun getRandomAnswerForLookingAround(room: Identifieable): String {
        return getRandomAnswerForSeed(
            """
                Generate an inner monologue for someone who looks around in 
                a $room. It should be quiet meaningless.
                """.trimIndent()
        )
    }

    fun getRandomAnswerForSeed(seed: String): String {
        return ChatGPTAdventure().getDynamicResponse(seed)
    }

}