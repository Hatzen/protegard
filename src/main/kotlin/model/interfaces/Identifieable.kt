package org.example.model.interfaces

interface Identifieable {

    val fullname: String

    fun preconditionToIdentify(): Boolean = true
}