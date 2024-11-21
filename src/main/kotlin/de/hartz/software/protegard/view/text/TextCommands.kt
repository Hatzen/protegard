package de.hartz.software.protegard.view.text

enum class TextCommands(val value: TextCommand) {
    // MENU(TextCommand("MENU", "m", TextIO::exit)),
    EXIT(TextCommand("EXIT", "x")),
    HELP(TextCommand("HELP", "h")),
    //SAVE(TextCommand("SAVE", "s")),
    //LOAD(TextCommand("LOAD", "l")),

    GOTO(TextCommand("GOTO", "g", 1)),
    SPEAKTO(TextCommand("SPEAKTO", "s", 1)),

    // TODO: Might need context, useitem on which object, key etc
    USEITEM(TextCommand("USEITEM", "m", 1, 2)),
    COMBINEITEM(TextCommand("COMBINEITEM", "m", 2)),


    // TODO: What exactly to offer here? Single Interaction for each or more general pass a list of possibilites per object?
    // DOACTION(TextCommand("DOACTION", "da")),

    // TODO: Would be cool to have generated texts, sounds playing, rooms available, cold or something
    //    Overall to enable room switches, objects and people
    LOOKAROUND(TextCommand("LOOKAROUND", "la")),

    LISTITEMS(TextCommand("LISTITEMS", "lsi")),
    LISTCONNECTIONS(TextCommand("LISTCONNECTIONS", "lsc")),
    LISTPEOPLE(TextCommand("LISTPEOPLE", "lsp")),
    // LISTACTIONS(TextCommand("LISTACTIONS", "lsa")),
    ;

}

class TextCommand(
    val key: String,
    val shortcut: String,
    val numberOfIdentifiersMin: Int = 0,
    val numberOfIdentifiersMax: Int = 0
) {

    constructor(key: String, shortcut: String, numberOfIdentifiers: Int = 0) :
            this(key, shortcut, numberOfIdentifiers, numberOfIdentifiers)

}