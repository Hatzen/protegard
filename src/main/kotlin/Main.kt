package org.example

import org.example.controller.GameController
import org.example.model.settings.Settings
import kotlin.math.max

fun main(args: Array<String>) {
    setupSettings(args)
    GameController.init()
}

fun setupSettings(args: Array<String>) {
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