package de.hartz.software.protegard.model.scenario.characters.main

import de.hartz.software.protegard.model.common.Character
import de.hartz.software.protegard.model.scenario.Items
import de.hartz.software.protegard.model.scenario.Rooms

/**
 * Ein gewöhnlicher Mann, vielleicht ein Lehrer oder Bibliothekar, der sich durch Zufall im Schloss wiederfindet. Er ist neugierig und scharfsinnig, aber zunächst überfordert von den mysteriösen Ereignissen. Seine Entwicklung hängt von den Entscheidungen des Spielers ab – er kann zum mutigen Beschützer oder zur skrupellosen Figur werden, die die Bedrohung aus Eigennutz für sich nutzt.
 */
class JonasLindgrenTheMainCharacter : Character("Jonas Lindgren", Rooms.villageEntry) {

    init {
        inventory.add(Items.watch)
        inventory.add(Items.lighter)
    }

    override fun preconditionToIdentify(): Boolean {
        // Dont identify yourself to interact.
        return false
    }
}