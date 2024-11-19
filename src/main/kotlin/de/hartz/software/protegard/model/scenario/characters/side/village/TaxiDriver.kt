package de.hartz.software.protegard.model.scenario.characters.side.village

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.common.dialog.Dialog
import de.hartz.software.protegard.model.scenario.Rooms

class TaxiDriver : Character("Taxi Driver", Rooms.villageEntry) {

    init {
        dialogs = Dialog(
            """
            Argg.. God dammit, the tire got broke. We need to stop here.
            I will need to repair before we can get to the castle. You can either try to walk the rest of the way
             or wait 2 to 3 hours until i got a replacement. 
            """.trimIndent(),
            mutableListOf(
                Dialog("No problem i need a walk after that long trip anyway.", target = this),
                Dialog("I dont know the exact way and wont keep my baggage on you so i will wait on that bench over there.", target = this)
            ),
            source = this,
            onlyOnce = true
        )
    }
}