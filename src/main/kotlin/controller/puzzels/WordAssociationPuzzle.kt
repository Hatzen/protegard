package controller.puzzels

// TODO: We need to add some tip system so the user can really get it..
class WordAssociationPuzzle {
    private val categories = mapOf(
        "Old Books" to listOf("Bible", "Odyssey", "Iliad", "Beowulf", "Canterbury"),
        "Animals" to listOf("Wolf", "Bear", "Eagle", "Deer", "Fox"),
        "Norway" to listOf("Fjord", "Oslo", "Viking", "Saga", "Troll"),
        "Scandinavia" to listOf("Rune", "Longship", "Aurora", "Shield", "Norse"),
        "Feelings" to listOf("Joy", "Fear", "Anger", "Sorrow", "Hope"),
        "Evolution" to listOf("Darwin", "Selection", "Adaptation", "Mutation", "Species"),
        "Danger" to listOf("Fire", "Storm", "Beast", "Battle", "Cliff"),
        "Dark Forest" to listOf("Shadow", "Whisper", "Path", "Tree", "Mist")
    )

    fun startGame(onCorrectAnswer: (setNumber: Int, elements: List<String>, correctWord: String) -> Unit) {
        repeat(10) { setNumber ->
            val category = categories.entries.random()
            val correctWord = category.value.random()

            // Shuffle elements to create some distraction
            val elements = category.value.toMutableList().apply { shuffle() }

            println("Set ${setNumber + 1}: Find the word that best associates with '${category.key}'.")
            println(elements.joinToString(", "))

            var attempts = 0
            while (true) {
                print("Enter the associated word: ")
                val userAnswer = readlnOrNull()?.trim()
                attempts++

                if (userAnswer.equals(correctWord, ignoreCase = true)) {
                    println("Correct! You solved it in $attempts attempts.")
                    onCorrectAnswer(setNumber + 1, elements, correctWord)
                    break
                } else {
                    println("Incorrect, try again.")
                }
            }
        }
    }
}

fun main() {
    val game = WordAssociationPuzzle()
    game.startGame { setNumber, elements, correctWord ->
        println("Great job! You found the correct associated word in set $setNumber.")
        println("Set: ${elements.joinToString(", ")}")
        println("The correct association was: $correctWord")
    }
}