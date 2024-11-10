package org.example.model.scenario.characters.main

import org.example.model.Character
import org.example.model.scenario.Rooms

class Narrator : Character("Narrator", Rooms.villageEntry) {

    val DIALOG_ENTRY = """
        Kapitel 1: Ankunft und erste Spuren
        Es ist das Jahr 1920, und Jonas Lindgren hat eine Einladung von einem entfernten Verwandten erhalten, der im Besitz eines alten Schlosses ist, das isoliert in den Bergen Skandinaviens liegt. Das Schloss hat eine lange und düstere Geschichte, und es wird gemunkelt, dass sich dort alte Geheimnisse verbergen. Jonas, ein neugieriger Gelehrter mit einer Leidenschaft für Geschichte, ist von der Idee fasziniert, die Schriften und Artefakte dieses Ortes zu studieren. Er hat keine Ahnung von der Seuche, die das nahegelegene Dorf heimgesucht hat – seine Reise ist rein akademisch motiviert.

        Als er jedoch in dem Dorf ankommt, merkt er schnell, dass etwas nicht stimmt. Die Bewohner sind krank, wirken verwirrt und manche scheinen geistig umnachtet zu sein. Sie sprechen von einer „Krankheit“, die die Gedanken korrumpiert und die Kranken in den Wahnsinn treibt. Doch Jonas ist entschlossen, das Schloss zu erreichen, da er glaubt, dass dort Antworten auf seine historischen Fragen liegen – vielleicht sogar auf die Ursache der Seuche.
    """.trimIndent()

}