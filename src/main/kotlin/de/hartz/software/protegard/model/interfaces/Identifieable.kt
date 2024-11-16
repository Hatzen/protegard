package de.hartz.software.protegard.model.interfaces

interface Identifieable {

    val fullname: String

    fun preconditionToIdentify(): Boolean = true
}