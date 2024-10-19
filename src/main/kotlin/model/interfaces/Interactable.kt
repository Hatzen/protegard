package org.example.model.interfaces

interface Interactable {

    fun interact()

    fun preconditionToInteract(): Boolean = true
}