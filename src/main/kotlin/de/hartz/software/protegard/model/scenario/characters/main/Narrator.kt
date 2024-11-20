package de.hartz.software.protegard.model.scenario.characters.main

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.scenario.Rooms

class Narrator : Character("Narrator", Rooms.hell) {

    val DIALOG_ENTRY = """
        Chapter 1: Arrival and first traces
        It is 1920, and Jonas Lindgren has received an invitation from a distant relative who owns an old castle isolated in the mountains of Scandinavia. The castle has a long and dark history, and it is rumored that ancient secrets are hidden there. Jonas, a curious scholar with a passion for history, is intrigued by the idea of studying the writings and artifacts of this place. He has no idea about the plague that has struck the nearby village - his journey is purely academic.
        
        However, when he arrives in the village, he quickly realizes that something is wrong. The inhabitants are ill, seem confused and some appear to be mentally deranged. They speak of a “disease” that corrupts their minds and drives the sick to madness. But Jonas is determined to reach the castle, as he believes that answers to his historical questions lie there - perhaps even the cause of the plague.
        """.trimIndent()

}