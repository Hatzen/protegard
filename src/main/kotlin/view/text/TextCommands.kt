package org.example.view.text

// TODO: create object with name, regex for attributes, shortcuts and Gamecontroler Method Call
class TextCommands {
    val MENU = "MENU"
    val EXIT = "EXIT"
    val SAVE = "SAVE"
    val LOAD = "LOAD"


    val GOTO = "GOTO"
    val SPEAKTO = "SPEAKTO"
    // TODO: Might need context, useitem on which object, key etc
    val USEITEM = "USEITEM"
    val COMBINEITEM = "COMBINEITEM"
    // TODO: What exactly to offer here? Single Interaction for each or more general pass a list of possibilites per object?
    val DOACTION = "DOACTION"

    // TODO: Would be cool to have generated texts, sounds playing, rooms available, cold or something
    //    Overall to enable room switches, objects and people
    val LOOKAROUND = "LOOKAROUND"

    val LISTITEMS = "LISTITEMS"
    val LISTCONNECTIONS = "LISTCONNECTIONS"
    val LISTPEOPLE = "LISTPEOPLE"
    val LISTACTIONS = "LISTACTIONS"
}