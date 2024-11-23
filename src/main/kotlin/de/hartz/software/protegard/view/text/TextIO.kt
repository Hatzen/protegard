package de.hartz.software.protegard.view.text

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.controller.GameController.getAllPeople
import de.hartz.software.protegard.controller.GameController.getAllRoomConnections
import de.hartz.software.protegard.controller.GameController.getAllRoomObjects
import de.hartz.software.protegard.controller.GameController.lookAround
import de.hartz.software.protegard.controller.IView
import de.hartz.software.protegard.controller.generative.ChatGPTAdventure
import de.hartz.software.protegard.model.interfaces.Identifieable
import de.hartz.software.protegard.model.settings.Settings
import de.hartz.software.protegard.view.text.TextCommands.*
import de.hartz.software.protegard.view.ui.ChapterAnimation

class TextIO(val translator: ChatGPTAdventure) : IView {
    private val scanner = ThreadSafeIO()
    private var scanInput = true
    private val realTimePrinter = RealTimePrinter()
    private var loadingSpinner: IOLoading? = IOLoading()

    override fun listenForUserInput() {
        while (scanInput) {
            val choice = scanner.getLine()
            try {
                // Split all words except within ""
                val reg = "\"[^\"]*\"|\\S+"
                val commandsAndParameters = Regex(reg).findAll(choice).map { it.value }.toList()
                if (commandsAndParameters.isEmpty()) {
                    continue // User pressed enter.
                }
                stopAnimation()
                loadingSpinner = IOLoading()
                handleChoice(commandsAndParameters)
                stopAnimation()
            } catch (ex: IllegalArgumentException) {
                // TODO: Remove when debugging finished. Overall logging would be nice but is just overhead?
                ex.printStackTrace()
                tellUser("Wrong parameters please try again.. Enter h for Help")
            }
        }

    }


    override fun showIntro() {
        tellUser("Type 'Help' or h to show commands")
        tellUser("Type 'Exit' or x to exit program")
        printHelp()
        tellUser("The Game begins..")

        if (Settings.useEffectsAndUi) {
            ChapterAnimation.showChapter(1)
        }
    }

    private fun handleChoice(commandsAndParameters: List<String>) {
        val command = commandsAndParameters.first()
        val foundCommand =
            TextCommands.entries.find { it.value.key.lowercase() == command || it.value.shortcut.lowercase() == command }
        if (foundCommand == null) {
            tellUser("'$command' is not a valid command. see h")
            return
        }

        val parameters = commandsAndParameters.subList(1, commandsAndParameters.size)
        val validParameterCount =
            IntRange(foundCommand.value.numberOfIdentifiersMin, foundCommand.value.numberOfIdentifiersMax)
        val actualParameterCount = parameters.size
        val properParameterCount = validParameterCount.contains(actualParameterCount)
        require(properParameterCount) { "$actualParameterCount vs $validParameterCount" }

        when (foundCommand) {
            EXIT -> {
                scanInput = false
                scanner.running = false
                GameController.exit()
            }

            HELP -> printHelp()
            //SAVE -> TODO()
            // LOAD -> TODO()
            GOTO -> {
                val room = getAllRoomConnections().find { isMatch(it.toRoom, parameters[0]) }
                requireNotNull(room) { " the entered room is not reachable or defined." }
                GameController.goto(room)
            }

            SPEAKTO -> {
                val character = getAllPeople().find { isMatch(it, parameters[0]) }
                requireNotNull(character)
                // TODO: Easier to just interact?
                GameController.startDialog(character)
            }

            USEITEM -> {
                val item = GameController.getInventory().find { isMatch(it, parameters[0]) }
                requireNotNull(item)
                // TODO: Move to Gamecontroller call?
                item.interact()
            }

            COMBINEITEM -> {
                val item1 = GameController.getInventory().find { isMatch(it, parameters[0]) }
                val item2 = GameController.getInventory().find { isMatch(it, parameters[1]) }
                requireNotNull(item1)
                requireNotNull(item2)

                // TODO: Move to Gamecontroller call?
                // Burn Paper or combine pen with paper
                item1.combine(item2)
            }

            DOACTION -> {
                // TODO: item on people, like kill them or item on objects, like burn them

                val item1 = getAllRoomObjects().find { isMatch(it, parameters[0]) }
                requireNotNull(item1)

                item1.interact()

            }

            LOOKAROUND -> {
                lookAround()
            }

            LISTITEMS -> {
                printIdentifiables(GameController.getInventory())
            }

            // TODO: The game would be more immersive if we only use look around.. But atm the answer is not reliable in answering the truth
            LISTCONNECTIONS ->
                printIdentifiables(getAllRoomConnections().map { it.toRoom })

            LISTPEOPLE ->
                printIdentifiables(getAllPeople())

            LISTACTIONS -> {
                printIdentifiables(getAllRoomObjects())
            }
        }
    }

    private fun isMatch(identifieable: Identifieable, identifier: String): Boolean {
        return identifieable.fullname.lowercase().startsWith(identifier.lowercase(), true)
    }

    private fun printHelp() {
        val objective = GameController.getCurrentObjective()
        tellUser("Your current Objective is: $objective")
        tellUser("Type any of the following commands not case sensitive, either full or the shortcut letters. The parameter are ids of rooms, items or people. Not case sensitiv as well. Especially you do not have to type the full name, but only the first letters until it is not ambigous. Ids may contain whitespaces, to properly distinguish different ids an id with spaces needs to be sourrounded by \"id\"")
        tellUser("When into a conversation type the (row) number of the answer and press enter afterwards to select it.")
        for (x in TextCommands.entries) {
            tellUser("Command '" + x.value.key + "' shortcut " + x.value.shortcut + " minParameter " + x.value.numberOfIdentifiersMin + " maxParameter " + x.value.numberOfIdentifiersMax)
        }
    }

    private fun printIdentifiables(list: List<Identifieable>) {
        tellUser("Available:")
        tellUser(list.map { it.fullname }.joinToString { "'$it', " })
    }

    override fun addText(text: String, source: Identifieable) {
        tellUser("'${source.fullname}': $text")
    }

    override fun addText(text: String) {
        tellUser(text)
    }


    override fun <T> getMultipleChoice(choice: List<T>): List<T> {
        do {
            try {
                addText("Write all numbers comma seperated to select items")
                var choiceText = ""
                for (i in choice.indices)
                    choiceText += "$i. $choice"
                addText(choiceText)

                // TODO: This filters invalid indexes but maybe retry would be better?
                return mapIndexesFromString(scanner.getMultipleChoice(), choice)
            } catch (ex: NoSuchElementException) {
                tellUser("Not a valid choice, please type just the number with a comma.")
            }
        } while (true)
    }

    private fun <T> mapIndexesFromString(input: List<Int>, list: List<T>): List<T> {
        return input
            .filter { it in list.indices }.map { list[it] }
    }


    override fun getChoice(): Int {
        do {
            try {
                return scanner.getChoice()
            } catch (ex: NoSuchElementException) {
                tellUser("Not a valid choice, please type just the number.")
            }
        } while (true)
    }

    private fun stopAnimation() {
        loadingSpinner?.stopAnimation()
        loadingSpinner = null
    }

    @Synchronized
    private fun tellUser(text: String) {
        stopAnimation()

        // TODO: translating makes only issues.
        //   https://community.openai.com/t/anyone-doing-successful-translations-with-gpt-3-5/326636/12
        // val translatedText = translator.translate(text)
        val translatedText = if (Settings.translate) {
            translator.translate(text)
        } else {
            text
        }
        if (Settings.useEffectsAndUi) {
            realTimePrinter.printMessage(translatedText)
        } else {
            println(translatedText)
        }
    }
}