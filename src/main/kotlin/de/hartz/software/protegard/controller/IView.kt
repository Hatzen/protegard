package de.hartz.software.protegard.controller

import de.hartz.software.protegard.model.interfaces.Identifieable

interface IView {

    fun listenForUserInput()

    fun addText(text: String, source: Identifieable)
    fun getChoice(): Int
    fun addText(text: String)
}