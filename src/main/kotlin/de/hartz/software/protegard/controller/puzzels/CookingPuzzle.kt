package de.hartz.software.protegard.controller.puzzels

import de.hartz.software.protegard.controller.IView
import de.hartz.software.protegard.model.scenario.Characters

object CookingPuzzle {
    enum class Score {
        PERFECT,
        NICE,
        OK,
        DISGUSTING
    }

    const val PASTA = "Pasta"
    const val TOMATO_SAUCE = "Tomato Sauce"
    const val CHEESE = "Cheese"
    const val OLIVES = "Olives"
    const val HAM = "Ham"
    const val MUSHROOMS = "Mushrooms"
    const val GARLIC = "Garlic"
    const val BASIL = "Basil"
    const val CHOCOLATE = "Chocolate"
    const val STRAWBERRIES = "Strawberries"
    const val LEMON = "Lemon"
    const val SALT = "Salt"
    const val PEPPER = "Pepper"
    const val CHILI = "CHILI"
    const val OLIVE_OIL = "Olive Oil"

    // Liste der verfügbaren Zutaten
    private val INGREDIENTS: List<String> = mutableListOf(
        PASTA, TOMATO_SAUCE, CHEESE, OLIVES, HAM,
        MUSHROOMS, GARLIC, BASIL, CHOCOLATE, STRAWBERRIES,
        LEMON, SALT, PEPPER, CHILI, OLIVE_OIL
    )

    // Methode zur Bewertung des Gerichts basierend auf der Zutatenliste
    private fun evaluateDish(selectedIngredients: List<String>): Int {
        // Basispunktzahl
        var score = 0

        // Gute Kombinationen, die die Punktzahl erhöhen
        if (selectedIngredients.contains(PASTA) && selectedIngredients.contains(TOMATO_SAUCE)) {
            score += 10
        }
        if (selectedIngredients.contains(CHEESE) && selectedIngredients.contains(OLIVES)) {
            score += 8
        }
        if (selectedIngredients.contains(GARLIC) && selectedIngredients.contains(BASIL)) {
            score += 7
        }
        if (selectedIngredients.contains(MUSHROOMS) && selectedIngredients.contains(OLIVE_OIL)) {
            score += 5
        }
        if (selectedIngredients.contains(HAM) && selectedIngredients.contains(PEPPER)) {
            score += 6
        }
        if (selectedIngredients.contains(LEMON) && selectedIngredients.contains(CHILI)) {
            score += 4
        }


        // Beste Kombination für ein sehr leckeres Gericht (alle Zutaten sollten enthalten sein)
        val bestCombination: List<String> = mutableListOf(
            PASTA, TOMATO_SAUCE, CHEESE, OLIVES,
            HAM, GARLIC, BASIL, OLIVE_OIL
        )
        if (selectedIngredients.containsAll(bestCombination)) {
            score += 30
        }

        // Schlechte Kombinationen, die die Punktzahl reduzieren
        if (selectedIngredients.contains(PASTA) && selectedIngredients.contains(CHOCOLATE)) {
            score -= 15 // Nudeln mit Schokolade schmecken nicht gut
        }
        if (selectedIngredients.contains(STRAWBERRIES) && selectedIngredients.contains(MUSHROOMS)) {
            score -= 10 // Erdbeeren und Pilze passen nicht gut zusammen
        }
        if (selectedIngredients.contains(LEMON) && selectedIngredients.contains(CHEESE)) {
            score -= 5 // Zitrone und Käse ist keine gute Kombination
        }

        return score
    }

    fun start(view: IView): Score {
        view.addText("Time to make tasty pasta, lets select the needed ingredients..", Characters.NARRATOR)

        val ingredient = view.getMultipleChoice(INGREDIENTS)
        val score = evaluateDish(ingredient)

        return if (score >= 30) {
            Score.PERFECT
        } else if (score >= 15) {
            Score.NICE
        } else if (score > 0) {
            Score.OK
        } else {
            Score.DISGUSTING
        }
    }
}