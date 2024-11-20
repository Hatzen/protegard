package de.hartz.software.protegard

import de.hartz.software.protegard.controller.GameController
import de.hartz.software.protegard.controller.LoggerUtil
import de.hartz.software.protegard.model.settings.Settings
import kotlin.math.max

fun main(args: Array<String>) {
    println("Loading application.. Might take a minute when using llms.")
    LoggerUtil.init()
    setupSettings(args)
    GameController.init()
    GameController.startGameFromBeginning()
}

fun setupSettings(args: Array<String>) {
    // TODO: Implement further settings
    var indexOf = max(args.indexOf("-a"), args.indexOf("useAi"))
    if (indexOf != -1) {
        if (args[indexOf + 1] == "false") {
            Settings.useLLMs = false
        }
    }
    indexOf = max(args.indexOf("-l"), args.indexOf("language"))
    if (indexOf != -1) {
        // TODO: Could be verified with llm
        Settings.language = args[indexOf + 1]
    }
}