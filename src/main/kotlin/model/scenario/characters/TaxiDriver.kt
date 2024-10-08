package org.example.model.scenario.characters

import org.example.model.Attributes
import org.example.model.Character
import org.example.model.Dialog
import org.example.model.scenario.Rooms

class TaxiDriver: Character("Taxi Driver", Rooms.villageEntry) {

    init {
        dialogs.add(Dialog("""
            Argg.. God dammit, the tire got broke. We need to stop here.
            I will need to repair before we can get to the castle. You can either try to walk the rest of the way
             or wait 2 to 3 hours until i got a replacement. 
            """.trimIndent(),
            listOf(
                Dialog("No problem i need a walk after that long trip anyway."),
                Dialog("I dont know the exact way and wont keep my baggaeg on you so i will wait on that bench over there.")
            ),
            onlyOnce = true

        )
        )
    }
}