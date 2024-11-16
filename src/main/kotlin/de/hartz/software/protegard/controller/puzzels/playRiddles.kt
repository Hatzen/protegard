package controller.puzzels

import java.util.*

fun playRiddles(riddles: List<Riddle>) {
    for (riddle in riddles) {
        println("\nRiddle: ${riddle.question}")
        print("Your answer: ")
        val userAnswer = readlnOrNull()?.trim()?.lowercase(Locale.getDefault())

        if (userAnswer == riddle.answer) {
            println("Correct! The answer is '${riddle.answer}'.")
        } else {
            println("Incorrect. The correct answer is '${riddle.answer}'.")
        }
    }
}

data class Riddle(val question: String, val answer: String)

fun main() {
    val riddles = listOf(
        Riddle("I can be cracked, made, told, and played. What am I?", "joke"),
        Riddle("I fly without wings, I cry without eyes. Whenever I go, darkness flies. What am I?", "cloud"),
        /*Riddle(
            "I have keys but open no locks. I have space but no room. You can enter, but you can’t go outside. What am I?",
            "keyboard"
        ),*/
        Riddle("I am not alive, but I can grow. I don’t have lungs, but I need air. What am I?", "fire"),
        Riddle("I have a neck but no head, and I wear a cap. What am I?", "bottle"),
        Riddle("I go up and down but never move. What am I?", "staircase"),
        Riddle("I have a face and two hands, but no arms or legs. What am I?", "clock"),
        Riddle("I can travel around the world while staying in a corner. What am I?", "stamp"),
        Riddle("I am tall when I am young, and I am short when I am old. What am I?", "candle"),
        Riddle("The more you take, the more you leave behind. What am I?", "footsteps")
    )

    println("Welcome to the Riddles Game!")
    playRiddles(riddles)
}