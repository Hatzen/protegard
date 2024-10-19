package org.example.model.interfaces

interface Identifieable {

    val name: String

    fun preconditionToIdentify(): Boolean = true
}