package org.example.view.text

import org.example.controller.GameController

enum class TextCommands(val value: TextCommand) {
    // MENU(TextCommand("MENU", "m", TextIO::exit)),
    EXIT(TextCommand("EXIT", "x", GameController::exit)),
    HELP(TextCommand("HELP", "h", GameController::exit)),
    SAVE(TextCommand("SAVE", "s", GameController::exit)),
    LOAD(TextCommand("LOAD", "l", GameController::exit)),

    GOTO(TextCommand("GOTO", "g", GameController::goto)),
    SPEAKTO(TextCommand("SPEAKTO", "s", 1)),
    // TODO: Might need context, useitem on which object, key etc
    USEITEM(TextCommand("USEITEM", "m", 1, 2)),
    COMBINEITEM(TextCommand("COMBINEITEM", "m", 2)),


    // TODO: What exactly to offer here? Single Interaction for each or more general pass a list of possibilites per object?
    DOACTION(TextCommand("DOACTION", "da")),
    // TODO: Would be cool to have generated texts, sounds playing, rooms available, cold or something
    //    Overall to enable room switches, objects and people
    LOOKAROUND(TextCommand("LOOKAROUND", "la")),

    LISTITEMS(TextCommand("LISTITEMS", "lsi")),
    LISTCONNECTIONS(TextCommand("LISTCONNECTIONS", "lsc")),
    LISTPEOPLE(TextCommand("LISTPEOPLE", "lsp")),
    LISTACTIONS(TextCommand("LISTACTIONS", "lsa")),
    ;

}

// TODO: create object with name, regex for attributes, shortcuts and Gamecontroler Method Call
class TextCommand(
    val key: String,
    val shortcut: String,
    val function: java.util.function.Function<*, *> ,
    val numberOfIdentifiersMin: Int = 0,
    val numberOfIdentifiersMax: Int = 0
) {

    constructor(key: String, shortcut: String, function: java.util.function.Function<*, *> , numberOfIdentifiers: Int = 0):
            this(key, shortcut, function, numberOfIdentifiers, numberOfIdentifiers)

}