package de.hartz.software.protegard.model.interfaces

interface Interactable {

    /*
    val DEFAULT_ACTION: String
        get() = "DEFAULT"

    fun interact() {
        interact(DEFAULT_ACTION)
    }

    fun interact(action: String)
     */

    fun interact()
    fun preconditionToInteract(): Boolean = true
}