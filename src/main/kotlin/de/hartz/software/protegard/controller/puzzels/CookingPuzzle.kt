package controller.puzzels

import java.util.*

// TODO: Build proper api, translate options.
object CookingPuzzle {
    // Liste der verfügbaren Zutaten
    private val INGREDIENTS: List<String> = mutableListOf(
        "Nudeln", "Tomatensauce", "Käse", "Oliven", "Schinken",
        "Pilze", "Knoblauch", "Basilikum", "Schokolade", "Erdbeeren",
        "Zitrone", "Salz", "Pfeffer", "Chili", "Olivenöl"
    )

    // Methode zur Bewertung des Gerichts basierend auf der Zutatenliste
    private fun evaluateDish(selectedIngredients: List<String>): Int {
        // Basispunktzahl
        var score = 0

        // Gute Kombinationen, die die Punktzahl erhöhen
        if (selectedIngredients.contains("Nudeln") && selectedIngredients.contains("Tomatensauce")) {
            score += 10
        }
        if (selectedIngredients.contains("Käse") && selectedIngredients.contains("Oliven")) {
            score += 8
        }
        if (selectedIngredients.contains("Knoblauch") && selectedIngredients.contains("Basilikum")) {
            score += 7
        }
        if (selectedIngredients.contains("Pilze") && selectedIngredients.contains("Olivenöl")) {
            score += 5
        }
        if (selectedIngredients.contains("Schinken") && selectedIngredients.contains("Pfeffer")) {
            score += 6
        }
        if (selectedIngredients.contains("Zitrone") && selectedIngredients.contains("Chili")) {
            score += 4
        }


        // Beste Kombination für ein sehr leckeres Gericht (alle Zutaten sollten enthalten sein)
        val bestCombination: List<String> = mutableListOf(
            "Nudeln", "Tomatensauce", "Käse", "Oliven",
            "Schinken", "Knoblauch", "Basilikum", "Olivenöl"
        )
        if (selectedIngredients.containsAll(bestCombination)) {
            score += 30
        }

        // Schlechte Kombinationen, die die Punktzahl reduzieren
        if (selectedIngredients.contains("Nudeln") && selectedIngredients.contains("Schokolade")) {
            score -= 15 // Nudeln mit Schokolade schmecken nicht gut
        }
        if (selectedIngredients.contains("Erdbeeren") && selectedIngredients.contains("Pilze")) {
            score -= 10 // Erdbeeren und Pilze passen nicht gut zusammen
        }
        if (selectedIngredients.contains("Zitrone") && selectedIngredients.contains("Käse")) {
            score -= 5 // Zitrone und Käse ist keine gute Kombination
        }

        return score
    }

    // Hauptmethode des Programms
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        val selectedIngredients: MutableList<String> = ArrayList()

        println("Willkommen zum Kochrätsel! Wähle bis zu 8 Zutaten, um ein leckeres Gericht zuzubereiten.")
        println("Verfügbare Zutaten: " + INGREDIENTS)
        println("Gib eine Zutat nach der anderen ein (oder 'fertig' um die Auswahl zu beenden):")

        while (selectedIngredients.size < 8) {
            print("Zutat: ")
            val input = scanner.nextLine().trim { it <= ' ' }

            if (input.equals("fertig", ignoreCase = true)) {
                break
            }

            if (!INGREDIENTS.contains(input)) {
                println("Ungültige Zutat. Wähle eine der verfügbaren Zutaten.")
                continue
            }

            if (selectedIngredients.contains(input)) {
                println("Du hast diese Zutat bereits ausgewählt.")
                continue
            }

            selectedIngredients.add(input)
            println("Zutat $input hinzugefügt.")
        }

        // Bewertung des Gerichts
        val score = evaluateDish(selectedIngredients)

        // Rückmeldung basierend auf der Punktzahl
        if (score >= 30) {
            println("Fantastisch! Dein Gericht ist ein kulinarisches Meisterwerk.")
        } else if (score >= 15) {
            println("Gut gemacht! Dein Gericht ist lecker.")
        } else if (score > 0) {
            println("Es ist essbar, aber nicht gerade ein Genuss.")
        } else {
            println("Leider ist dein Gericht ungenießbar.")
        }

        println("Deine Punktzahl: $score")
        println("Ausgewählte Zutaten: $selectedIngredients")

        scanner.close()
    }
}